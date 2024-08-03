package com.ot.BoboLike.dao;

import com.ot.BoboLike.dto.Admin;
import com.ot.BoboLike.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminDao {

    @Autowired
    private AdminRepository adminRepository;

    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public Admin findByPhone(String phone) {
        return adminRepository.findByPhone(phone);
    }


    public void delete(Admin admin) {
        adminRepository.delete(admin);
    }

    public Optional<Admin> findById(long id) {
        return adminRepository.findById(id);
    }

    public Page<Admin> findAll(int offset, int pageSize, String field) {
        return adminRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field).descending()));
    }

    public List<Admin> findByNameContaining(String letter) {
        return adminRepository.findByNameContaining(letter);
    }

    public Admin findByOtp(String otp) {
        return adminRepository.findByOtp(otp);
    }
}
