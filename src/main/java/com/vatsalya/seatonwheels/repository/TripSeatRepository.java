package com.vatsalya.seatonwheels.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.vatsalya.seatonwheels.entity.TripSeat;


public interface TripSeatRepository extends JpaRepository<TripSeat, Long> {
}
