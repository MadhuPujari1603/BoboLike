package com.ot.BoboLike.service;

import com.ot.BoboLike.dao.AdminDao;
import com.ot.BoboLike.dto.Admin;
import com.ot.BoboLike.dto.ResponseStructure;
import com.ot.BoboLike.dto.request.AdminRegister;
import com.ot.BoboLike.repository.AdminRepository;
import com.ot.BoboLike.util.Aes;
import com.ot.BoboLike.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private EmailSender mailSender;


    @Autowired
    private AdminRepository adminRepository;

    public ResponseEntity<ResponseStructure<Admin>> save(AdminRegister admin) {
        ResponseStructure<Admin> responseStructure = new ResponseStructure<>();

        if (adminDao.findByEmail(admin.getEmail()) != null || adminDao.findByPhone(admin.getPhone()) != null) {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Admin already exists to save ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        } else {
            Admin sAdmin = new Admin();
            sAdmin.setEmail(admin.getEmail());
            sAdmin.setPhone(admin.getPhone());
            sAdmin.setAddress(admin.getAddress());
            sAdmin.setName(admin.getName());
            sAdmin.setPassword(Aes.encrypt(admin.getPassword()));
            adminDao.save(sAdmin);

            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("Admin saved successfully ");
            responseStructure.setData(sAdmin);
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
        }
    }


    public ResponseEntity<ResponseStructure<Admin>> findById(long id) {
        ResponseStructure<Admin> responseStructure = new ResponseStructure<>();
        Optional<Admin> optionalAdmin = adminDao.findById(id);

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Admin found by id " + id);
            responseStructure.setData(admin);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Admin does not exist ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<ResponseStructure<Page<Admin>>> findAll(int offset, int pageSize, String field) {
        ResponseStructure<Page<Admin>> responseStructure = new ResponseStructure<>();

        Page<Admin> admins = adminDao.findAll(offset, pageSize, field);
        if (admins != null) {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Found all the data of admins ");
            responseStructure.setData(admins);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("No data exists to find of admins");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<Admin>> update(long id, AdminRegister adminRegister) {
        ResponseStructure<Admin> responseStructure = new ResponseStructure<>();

        Optional<Admin> optionalAdmin = adminDao.findById(id);
        if (!optionalAdmin.isPresent()) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Admin not found to update ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }

        Admin existingAdmin = optionalAdmin.get();

        // Check for email conflict
        if (adminRegister.getEmail() != null &&
                adminDao.findByEmail(adminRegister.getEmail()) != null &&
                !existingAdmin.getEmail().equals(adminRegister.getEmail())) {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Email already in use ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        }
        if (adminRegister.getPhone() != null &&
                adminDao.findByPhone(adminRegister.getPhone()) != null &&
                !existingAdmin.getPhone().equals(adminRegister.getPhone())) {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Phone already in use ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        }
        if (adminRegister.getName() != null) {
            existingAdmin.setName(adminRegister.getName());
        }
        if (adminRegister.getPhone() != null) {
            existingAdmin.setPhone(adminRegister.getPhone());
        }
        if (adminRegister.getEmail() != null) {
            existingAdmin.setEmail(adminRegister.getEmail());
        }
        if (adminRegister.getPassword() != null) {
            existingAdmin.setPassword(Aes.encrypt(adminRegister.getPassword()));
        }
        if (adminRegister.getAddress() != null) {
            existingAdmin.setAddress(adminRegister.getAddress());
        }

        adminDao.save(existingAdmin);

        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setMessage("Admin updated successfully ");
        responseStructure.setData(existingAdmin);
        return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    }
}
