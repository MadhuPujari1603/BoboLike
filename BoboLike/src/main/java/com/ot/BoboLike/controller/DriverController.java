package com.ot.BoboLike.controller;

import com.ot.BoboLike.dto.Driver;
import com.ot.BoboLike.dto.ResponseStructure;
import com.ot.BoboLike.dto.request.DriverRegister;
import com.ot.BoboLike.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    public DriverService driverService;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseStructure<Driver>> save(
                                                          @RequestBody DriverRegister driver) {
        return driverService.save(driver);
    }

 /*   @PostMapping(value = "/update/{id}")
    public ResponseEntity<ResponseStructure<Driver>> update(@PathVariable long id,
                                                            @RequestBody DriverRegister driver) {
        return driverService.update(id, driver);
    }*/

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseStructure<Driver>> delete(@RequestParam long id) {
        return driverService.delete(id);
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<ResponseStructure<Driver>> findById(@RequestParam long id) {
        return driverService.findById(id);
    }


    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseStructure<Page<Driver>>> findAll(@RequestParam(defaultValue = "0") int offset,
                                                                   @RequestParam(defaultValue = "5") int pageSize,
                                                                   @RequestParam String field) {

        return driverService.findAll(offset, pageSize, field);
    }

}
