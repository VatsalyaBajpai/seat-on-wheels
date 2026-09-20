package com.vatsalya.seatonwheels.controller;


import com.vatsalya.seatonwheels.entity.Booking;
import com.vatsalya.seatonwheels.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService)
    {
        this.bookingService=bookingService;
    }

    @PostMapping
    public Booking createBooking(
            @RequestParam Long userId,
            @RequestParam Long tripSeatId
            )
    {
        return bookingService.createBooking(userId,tripSeatId);
    }
}
