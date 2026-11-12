package com.vatsalya.seatonwheels.controller;


import com.vatsalya.seatonwheels.entity.Booking;
import com.vatsalya.seatonwheels.entity.User;
import com.vatsalya.seatonwheels.service.BookingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.vatsalya.seatonwheels.dto.BookingResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService)
    {
        this.bookingService=bookingService;
    }

    @PostMapping
    public BookingResponseDTO createBooking(
            @AuthenticationPrincipal User user,
            @RequestParam Long tripSeatId
            )
    {
        return bookingService.createBooking(user.getId(),tripSeatId);
    }

    @GetMapping("/user/{userId}")
    public List<BookingResponseDTO> getUserBookings(@PathVariable("userId") Long userId)
    {
        return bookingService.getBookingsByUser(userId);
    }
    @PutMapping("/{bookingId}/cancel")
    public BookingResponseDTO cancelBooking(
            @PathVariable("bookingId") Long bookingId,
            @AuthenticationPrincipal User user
    ) {
        return bookingService.cancelBooking(bookingId, user.getId());
    }
}
