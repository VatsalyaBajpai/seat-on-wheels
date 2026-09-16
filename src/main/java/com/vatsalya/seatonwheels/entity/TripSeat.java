package com.vatsalya.seatonwheels.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="trip_seats")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor


public class TripSeat {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="trip_id" , nullable=false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name="seat_id" , nullable=false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;


}
