package com.vatsalya.seatonwheels.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.vatsalya.seatonwheels.entity.Trip;


public interface TripRepository extends JpaRepository<Trip, Long> {
}
