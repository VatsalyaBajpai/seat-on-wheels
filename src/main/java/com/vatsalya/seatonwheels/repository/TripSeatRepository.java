package com.vatsalya.seatonwheels.repository;


import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vatsalya.seatonwheels.entity.TripSeat;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface TripSeatRepository extends JpaRepository<TripSeat, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ts FROM TripSeat ts WHERE ts.id = :id")
    Optional<TripSeat> findByIdForUpdate(@Param("id") Long id);
}
