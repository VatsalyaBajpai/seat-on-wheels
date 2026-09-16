package com.vatsalya.seatonwheels.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buses")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String busNumber;

    private String busType;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Seat> seats = new ArrayList<>();
}

//one bus can have multiple seats ,