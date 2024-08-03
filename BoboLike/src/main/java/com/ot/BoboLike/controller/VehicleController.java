package com.ot.BoboLike.controller;

import com.ot.BoboLike.dto.Driver;
import com.ot.BoboLike.dto.ResponseStructure;
import com.ot.BoboLike.dto.Vehicle;
import com.ot.BoboLike.dto.request.DriverRegister;
import com.ot.BoboLike.dto.request.VehicleRegister;
import com.ot.BoboLike.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseStructure<Vehicle>> save(@RequestBody VehicleRegister vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }
}
