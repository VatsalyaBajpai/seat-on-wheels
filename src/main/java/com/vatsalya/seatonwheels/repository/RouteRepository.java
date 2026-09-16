package com.vatsalya.seatonwheels.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.vatsalya.seatonwheels.entity.Route;



public interface RouteRepository extends JpaRepository<Route, Long> {
}
