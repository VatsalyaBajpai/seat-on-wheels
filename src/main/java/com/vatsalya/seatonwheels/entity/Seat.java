package com.vatsalya.seatonwheels.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name="seats")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String seatNumber;

    private String seatType;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;
}
