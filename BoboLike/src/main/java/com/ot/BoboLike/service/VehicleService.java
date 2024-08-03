package com.ot.BoboLike.service;

import com.ot.BoboLike.dao.VehicleDao;
import com.ot.BoboLike.dto.Driver;
import com.ot.BoboLike.dto.ResponseStructure;
import com.ot.BoboLike.dto.Vehicle;
import com.ot.BoboLike.dto.request.VehicleRegister;
import com.ot.BoboLike.repository.DriverRepository;
import com.ot.BoboLike.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private VehicleDao vehicleDao;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public ResponseEntity<ResponseStructure<Vehicle>> saveVehicle(VehicleRegister vehicleRegister) {
        ResponseStructure<Vehicle> responseStructure = new ResponseStructure<>();

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName(vehicleRegister.getVehicleName());
        vehicle.setVehicleNumberPlate(vehicleRegister.getVehicleNumber());
        vehicle.setModelOfYear(vehicleRegister.getModelOfYear());
        vehicle.setVehicleType(vehicleRegister.getVehicleType());
        vehicle.setFuelType(vehicleRegister.getFuelType());
        vehicle.setOccupancy(vehicleRegister.getOccupancy());

        try {
            if (vehicleRepository.findByVehicleNumberPlate(vehicle.getVehicleNumberPlate()) != null) {
                responseStructure.setStatus(HttpStatus.CONFLICT.value());
                responseStructure.setMessage("Vehicle with number plate " + vehicle.getVehicleNumberPlate() + " already exists.");
                responseStructure.setData(null);
                return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
            }

            Vehicle savedVehicle = vehicleRepository.save(vehicle);

            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("Vehicle Saved Successfully");
            responseStructure.setData(savedVehicle);
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        } catch (Exception e) {
            responseStructure.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseStructure.setMessage("Failed to save Vehicle: " + e.getMessage());
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
