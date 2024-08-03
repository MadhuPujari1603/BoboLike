package com.ot.BoboLike.repository;

import com.ot.BoboLike.dto.BookingStatus;
import com.ot.BoboLike.dto.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    public Driver findByEmail(String email);

    public Driver findByPhone(String phone);

    public Driver findByOtp(String otp);

    public List<Driver> findByNameContaining(String letter);

    public   Optional<Driver> findFirstByStatus(BookingStatus bookingStatus);
}

