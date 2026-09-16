package com.vatsalya.seatonwheels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vatsalya.seatonwheels.entity.Bus;


public interface BusRepository extends JpaRepository<Bus, Long>{
}
