package com.ot.BoboLike.dao;

import com.ot.BoboLike.dto.BookingStatus;
import com.ot.BoboLike.dto.Driver;
import com.ot.BoboLike.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DriverDao {

    @Autowired
    private DriverRepository driverRepository;

    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver findByEmail(String email) {
        return driverRepository.findByEmail(email);
    }

    public Driver findByPhone(String phone) {
        return driverRepository.findByPhone(phone);
    }

    public void delete(Driver staff) {
        driverRepository.delete(staff);
    }

    public Driver update(Driver driver) {
        return driverRepository.save(driver);
    }

    public Driver findById(long id) {
        Optional<Driver> driver = driverRepository.findById(id);
        return driver.orElse(null);
    }

    public Page<Driver> findAll(int offset, int pageSize, String field) {
        return driverRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field).descending()));
    }

    public List<Driver> findByNameContaining(String letter) {
        return driverRepository.findByNameContaining(letter);
    }

    public Driver findByOtp(String otp) {
        return driverRepository.findByOtp(otp);
    }

    public Optional<Driver> findFirstByStatus(BookingStatus bookingStatus){
        return driverRepository.findFirstByStatus(bookingStatus);
    }

}
