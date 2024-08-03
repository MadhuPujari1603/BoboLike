package com.ot.BoboLike.service;

import com.ot.BoboLike.dao.DriverDao;
import com.ot.BoboLike.dao.VehicleDao;
import com.ot.BoboLike.dto.Driver;
import com.ot.BoboLike.dto.ResponseStructure;
import com.ot.BoboLike.dto.Vehicle;
import com.ot.BoboLike.dto.request.DriverRegister;
import com.ot.BoboLike.repository.AdminRepository;
import com.ot.BoboLike.repository.DriverRepository;
import com.ot.BoboLike.util.Aes;
import com.ot.BoboLike.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    public DriverDao driverDao;

    @Autowired
    public DriverRepository driverRepository;

    @Autowired
    private EmailSender mailSender;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private VehicleDao vehicleDao;

    public ResponseEntity<ResponseStructure<Driver>> save(DriverRegister driverRegister) {
        ResponseStructure<Driver> responseStructure = new ResponseStructure<>();
        try {
            if (driverDao.findByEmail(driverRegister.getEmail()) != null ||
                    driverDao.findByPhone(driverRegister.getPhone()) != null) {
                responseStructure.setStatus(HttpStatus.CONFLICT.value());
                responseStructure.setMessage("Driver Could Not Be Saved, Already Exists");
                responseStructure.setData(null);
                return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
            }
            Driver driver = new Driver();
            driver.setFirstName(driverRegister.getFirstName());
            driver.setLastName(driverRegister.getLastName());
            driver.setName(driverRegister.getFirstName() + " " + driverRegister.getLastName());
            driver.setLicenseNumber(driverRegister.getLicenseNumber());
            driver.setPhone(driverRegister.getPhone());
            driver.setEmail(driverRegister.getEmail());
            driver.setAddress(driverRegister.getAddress());
            driver.setPassportNumber(driverRegister.getPassportNumber());
            driver.setLicenseImage(driverRegister.getLicenseImage());
            driver.setPassportImage(driverRegister.getPassportImage());
            driver.setPassword(Aes.encrypt(driverRegister.getPassword()));
            driver.setNationality(driverRegister.getNationality());
            if (driverRegister.getVehicleId() != 0) {
                Vehicle vehicle = vehicleDao.findById(driverRegister.getVehicleId());
                if (vehicle != null) {
                    driver.setVehicle(vehicle);
                } else {
                    responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
                    responseStructure.setMessage("Vehicle with ID " + driverRegister.getVehicleId() + " not found.");
                }
            }
            Driver savedDriver = driverRepository.save(driver);
            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("Driver Saved Successfully");
            responseStructure.setData(savedDriver);
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        } catch (Exception e) {
            responseStructure.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseStructure.setMessage("Failed to save Driver: " + e.getMessage());
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<ResponseStructure<Driver>> findById(long id) {
        ResponseStructure<Driver> responseStructure = new ResponseStructure<>();

        Driver optionalDriver = driverDao.findById(id);
        if (optionalDriver == null) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Driver does not exist to be found by ID ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }

        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Driver found by ID = " + id);
        responseStructure.setData(optionalDriver);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }


    public ResponseEntity<ResponseStructure<Driver>> delete(long id) {
        ResponseStructure<Driver> responseStructure = new ResponseStructure<>();

        Driver optionalDriver = driverDao.findById(id);
        if (optionalDriver != null) {
            driverDao.delete(optionalDriver);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Driver deleted successfully ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Driver not found to delete ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<ResponseStructure<Page<Driver>>> findAll(int offset, int pageSize, String field) {
        ResponseStructure<Page<Driver>> responseStructure = new ResponseStructure<>();

        Page<Driver> driverPage = driverDao.findAll(offset, pageSize, field);
        if (!driverPage.isEmpty()) {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("All Driver found ");
            responseStructure.setData(driverPage);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("No Driver Found ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
    }

    /*public ResponseEntity<ResponseStructure<Driver>> update(long id, DriverRegister driverRegister) {
        ResponseStructure<Driver> responseStructure = new ResponseStructure<>();

        Driver driver = driverDao.findById(id);
        if (driver == null) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Driver not found to update ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }

        if (driverRegister.getFirstName() != null) {
            driver.setFirstName(driverRegister.getFirstName());
        }
        if (driverRegister.getPhone() != null) {
            existingDriver.setPhone(driverRegister.getPhone());
        }
        if (driverRegister.getEmail() != null) {
            existingDriver.setEmail(driverRegister.getEmail());
        }
        if (driverRegister.getPassword() != null) {
            existingDriver.setPassword(Aes.encrypt(driverRegister.getPassword()));
        }
        if (driverRegister.getAddress() != null) {
            existingDriver.setAddress(driverRegister.getAddress());
        }
        driverDao.save(existingDriver);

        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Driver updated successfully ");
        responseStructure.setData(existingDriver);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }*/

}