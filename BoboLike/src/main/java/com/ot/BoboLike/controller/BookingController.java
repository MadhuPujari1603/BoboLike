package com.ot.BoboLike.controller;

import com.ot.BoboLike.dto.Booking;
import com.ot.BoboLike.dto.ResponseStructure;
import com.ot.BoboLike.dto.request.BookingInitiate;
import com.ot.BoboLike.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/save")
    public ResponseEntity<ResponseStructure<Booking>> createBooking(@RequestBody BookingInitiate booking) {
        return bookingService.assignDriver(booking);
    }

    @PutMapping("/complete/{bookingId}")
    public ResponseEntity<ResponseStructure<Booking>> completeBooking(@PathVariable Long bookingId) {
        return bookingService.completeBooking(bookingId);
    }

}
