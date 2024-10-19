package com.ot.BoboLike.dao;

import com.ot.BoboLike.dto.Admin;
import com.ot.BoboLike.dto.Booking;
import com.ot.BoboLike.dto.Driver;
import com.ot.BoboLike.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookingDao {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> findById(long id) {
        return bookingRepository.findById(id);
    }

    public Page<Booking> findAll(int offset, int pageSize, String field) {
        return bookingRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field).descending()));
    }
    public Page<Booking> findByDriverId(long driverId,int offset, int pageSize, String field) {
        return bookingRepository.findByDriverId(PageRequest.of(offset, pageSize).withSort(Sort.by(field).descending()));
    }

}
