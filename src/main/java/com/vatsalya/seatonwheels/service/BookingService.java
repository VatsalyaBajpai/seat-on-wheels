package com.vatsalya.seatonwheels.service;


import com.vatsalya.seatonwheels.entity.*;
import com.vatsalya.seatonwheels.repository.BookingRepository;
import com.vatsalya.seatonwheels.repository.TripRepository;
import com.vatsalya.seatonwheels.repository.TripSeatRepository;
import com.vatsalya.seatonwheels.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.vatsalya.seatonwheels.exception.*;
import com.vatsalya.seatonwheels.dto.BookingResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class BookingService {

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    private final TripSeatRepository tripSeatRepository;

    public BookingService(
            UserRepository userRepository,
            BookingRepository bookingRepository,
            TripSeatRepository tripSeatRepository
    ) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.tripSeatRepository = tripSeatRepository;
    }


    @Transactional
    public BookingResponseDTO createBooking(Long userId, Long tripSeatId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        TripSeat tripSeat = tripSeatRepository.findByIdForUpdate(tripSeatId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip seat not found with id: " + tripSeatId));

        if (tripSeat.getStatus() == SeatStatus.BOOKED)
            throw new SeatAlreadyBookedException("Seat with id " + tripSeatId + " is already booked");

        tripSeat.setStatus(SeatStatus.BOOKED);

        tripSeatRepository.save(tripSeat);

        Booking booking = new Booking();

        booking.setUser(user);
        booking.setTrip(tripSeat.getTrip());
        booking.setBookingTime(LocalDateTime.now());
        booking.setTripSeat(tripSeat);
        booking.setStatus(BookingStatus.CONFIRMED);
        Booking savedBooking = bookingRepository.save(booking);
        return mapToDTO(savedBooking);

        //return bookingRepository.save(booking);
    }

    @Transactional
    public BookingResponseDTO cancelBooking(Long bookingId, Long userId) {
        //to fetch existing booking
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("booking not found with id:" + bookingId));

        //owner verification
        if (!booking.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("Unauthorized: Booking does not belong to user id :" + userId);
        }

        //state validatation
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BookingAlreadyCancelledException("Booking with id:" + bookingId + " is already cancelled");
        }

        //acquire pessimistic locking
        TripSeat tripSeat = tripSeatRepository.findByIdForUpdate(booking.getTripSeat().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Trip seat not found"));

        //release the inventory back to available
        tripSeat.setStatus(SeatStatus.AVAILABLE);
        tripSeatRepository.save(tripSeat);
        booking.setStatus(BookingStatus.CANCELLED);

        Booking savedBooking = bookingRepository.save(booking);
        return mapToDTO(savedBooking);

        //return bookingRepository.save(booking);
    }


    public List<BookingResponseDTO> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private BookingResponseDTO mapToDTO(Booking booking) {
        return BookingResponseDTO.builder()
                .bookingId(booking.getId())
                .userId(booking.getUser().getId())
                .userName(booking.getUser().getName())
                .busNumber(booking.getTrip().getBus().getBusNumber())
                .seatNumber(booking.getTripSeat().getSeat().getSeatNumber())
                .source(booking.getTrip().getRoute().getSource())
                .destination(booking.getTrip().getRoute().getDestination())
                .departureTime(booking.getTrip().getDepartureTime())
                .fare(booking.getTrip().getFare())
                .bookingTime(booking.getBookingTime())
                .status(booking.getStatus())
                .build();
    }
}
