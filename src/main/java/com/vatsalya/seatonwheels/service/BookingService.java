package com.vatsalya.seatonwheels.service;


import com.vatsalya.seatonwheels.entity.*;
import com.vatsalya.seatonwheels.repository.BookingRepository;
import com.vatsalya.seatonwheels.repository.TripRepository;
import com.vatsalya.seatonwheels.repository.TripSeatRepository;
import com.vatsalya.seatonwheels.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class BookingService {

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    private final TripSeatRepository tripSeatRepository;

    public BookingService(
                          UserRepository userRepository,
                          BookingRepository bookingRepository,
                          TripSeatRepository tripSeatRepository
                         )
    {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.tripSeatRepository = tripSeatRepository;
    }


    @Transactional
    public Booking createBooking(Long userId, Long tripSeatId)
    {
        User user=userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        TripSeat tripSeat=tripSeatRepository.findByIdForUpdate(tripSeatId)
                .orElseThrow(() ->new RuntimeException("Trip seat not found"));

        if(tripSeat.getStatus()== SeatStatus.BOOKED)
            throw new RuntimeException("Seat already Booked");

        tripSeat.setStatus(SeatStatus.BOOKED);

        tripSeatRepository.save(tripSeat);

        Booking booking=new Booking();

        booking.setUser(user);
        booking.setTrip(tripSeat.getTrip());
        booking.setBookingTime(LocalDateTime.now());
        booking.setTripSeat(tripSeat);
        booking.setStatus(BookingStatus.CONFIRMED);

        return bookingRepository.save(booking);
    }
}
