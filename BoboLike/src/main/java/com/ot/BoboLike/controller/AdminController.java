package com.ot.BoboLike.controller;

import com.ot.BoboLike.dto.Admin;
import com.ot.BoboLike.dto.ResponseStructure;
import com.ot.BoboLike.dto.request.AdminRegister;
import com.ot.BoboLike.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminservice;

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseStructure<Admin>> save(@RequestBody AdminRegister admin) {
        return adminservice.save(admin);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseStructure<Admin>> update(@RequestParam long id,
                                                           @RequestBody AdminRegister admin) {
        return adminservice.update(id, admin);
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<ResponseStructure<Admin>> findById(@RequestParam long id) {
        return adminservice.findById(id);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<ResponseStructure<Page<Admin>>> findAll(@RequestParam(defaultValue = "0") int offset,
                                                                  @RequestParam(defaultValue = "5") int pageSize,
                                                                  @RequestParam(defaultValue = "id") String field) {
        return adminservice.findAll(offset, pageSize, field);
    }
}