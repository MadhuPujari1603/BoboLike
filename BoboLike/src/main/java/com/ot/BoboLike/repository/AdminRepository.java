package com.ot.BoboLike.repository;

import com.ot.BoboLike.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    public Admin findByEmail(String email);

    public Admin findByPhone(String phone);

    public Admin findByOtp(String otp);

    public List<Admin> findByNameContaining(String letter);

}