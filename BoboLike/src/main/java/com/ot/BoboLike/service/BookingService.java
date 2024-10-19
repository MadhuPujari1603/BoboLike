package com.ot.BoboLike.service;

import com.ot.BoboLike.dao.BookingDao;
import com.ot.BoboLike.dao.DriverDao;
import com.ot.BoboLike.dto.*;
import com.ot.BoboLike.dto.request.BookingInitiate;
import com.ot.BoboLike.repository.BookingRepository;
import com.ot.BoboLike.repository.DriverRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private DriverDao driverDao;

    public ResponseEntity<ResponseStructure<Booking>> assignDriver(BookingInitiate initiate) {
        ResponseStructure<Booking> responseStructure = new ResponseStructure<>();
        Driver driver = driverDao.findById(initiate.getDriverId());
        if (Objects.isNull(driver)) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Driver Id Not Found " + initiate.getDriverId());
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        if (driver.getStatus().equals("UNAVAILABLE")) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Driver Unavailable " + driver.getName());
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
        Booking booking = new Booking();
        booking.setDriver(driver);
        booking.setCustomerName(initiate.getCustomerName());
        booking.setCustomerNumber(initiate.getCustomerNumber());
        booking.setPickupLocationlatitude(initiate.getPickupLocationlatitude());
        booking.setPickupLocationlongitude(initiate.getPickupLocationlongitude());
        booking.setDropLocationLatitude(initiate.getDropLocationLatitude());
        booking.setDropLocationLongitude(initiate.getDropLocationLongitude());
        booking.setStatus(BookingStatus.ASSIGNED);
        bookingDao.save(booking);
        driver.setStatus(DriverStatus.UNAVAILABLE);
        driverDao.save(driver);
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setMessage("Booking Created and Driver Assigned of Name -> " + driver.getName());
        responseStructure.setData(null);
        return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<Booking>> completeBooking(Long bookingId) {
        ResponseStructure<Booking> responseStructure = new ResponseStructure<>();
        try {
            Booking booking = bookingDao.findById(bookingId).orElse(null);
            if (booking == null) {
                responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
                responseStructure.setMessage("Booking not found for ID: " + bookingId);
                responseStructure.setData(null);
                return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
            }
            Driver driver = booking.getDriver();
            if (driver == null) {
                responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
                responseStructure.setMessage("Driver not found for Booking ID: " + bookingId);
                responseStructure.setData(null);
                return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
            }
            booking.setStatus(BookingStatus.COMPLETED);
            driver.setStatus(DriverStatus.AVAILABLE);
            bookingDao.save(booking);
            driverDao.save(driver);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Booking completed and driver status updated to available.");
            responseStructure.setData(booking);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } catch (Exception e) {
            responseStructure.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseStructure.setMessage("Failed to complete booking: " + e.getMessage());
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseStructure<Booking>> findById(long id) {
        ResponseStructure<Booking> responseStructure = new ResponseStructure<>();
        Optional<Booking> booking = bookingDao.findById(id);
        if (booking.isPresent()) {
            Booking booking1 = booking.get();
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Admin found by id " + id);
            responseStructure.setData(booking1);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Admin does not exist ");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseStructure<Page<Booking>>> findAll(int offset, int pageSize, String field) {
        ResponseStructure<Page<Booking>> responseStructure = new ResponseStructure<>();
        Page<Booking> bookings = bookingDao.findAll(offset, pageSize, field);
        if (bookings != null) {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Found all the data of admins ");
            responseStructure.setData(bookings);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("No data exists to find of admins");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
    }



    public ResponseEntity<ResponseStructure<Page<Booking>>> findBookingsByDriverId(long driverId,int offset, int pageSize, String field) {
        ResponseStructure<Page<Booking>> responseStructure = new ResponseStructure<>();
        Page<Booking> bookings = bookingDao.findByDriverId(driverId,offset, pageSize, field);
        if (!bookings.isEmpty()) {
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Bookings found for driver id " + driverId);
            responseStructure.setData(bookings);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        } else {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("No bookings found for driver id " + driverId);
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        }
    }

}