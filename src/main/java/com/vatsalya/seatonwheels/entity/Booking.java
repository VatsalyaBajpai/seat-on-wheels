package com.vatsalya.seatonwheels.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name="booking")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;


    @ManyToOne
    @JoinColumn(name="trip_id", nullable=false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name="trip_seat_id", nullable=false)
    private TripSeat tripSeat;

    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}
