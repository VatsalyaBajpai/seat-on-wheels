package com.vatsalya.seatonwheels.repository;


import com.vatsalya.seatonwheels.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
