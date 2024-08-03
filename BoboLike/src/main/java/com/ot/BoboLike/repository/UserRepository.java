package com.ot.BoboLike.repository;

import com.ot.BoboLike.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByPhone(String phone);

    public User findByEmail(String email);

    public List<User> findByNameContaining(String letter);

    public User findByOtp(String otp);
}