package com.ot.BoboLike.repository;

import com.ot.BoboLike.dto.Booking;
import com.ot.BoboLike.dto.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    public Page<Booking> findByDriver(Driver driver, Pageable pageable);

    public Page<Booking> findByDriverId(long driverId,Pageable pageable);
}