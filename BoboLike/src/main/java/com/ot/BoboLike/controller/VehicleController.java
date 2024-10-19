package com.ot.BoboLike.controller;

import com.ot.BoboLike.dto.Driver;
import com.ot.BoboLike.dto.ResponseStructure;
import com.ot.BoboLike.dto.Vehicle;
import com.ot.BoboLike.dto.request.DriverRegister;
import com.ot.BoboLike.dto.request.VehicleRegister;
import com.ot.BoboLike.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseStructure<Page<Vehicle>>> findAll(@RequestParam(defaultValue = "0") int offset,
                                                                    @RequestParam(defaultValue = "5") int pageSize,
                                                                    @RequestParam(defaultValue = "id") String field) {
        return vehicleService.findAll(offset, pageSize, field);
    }

}
