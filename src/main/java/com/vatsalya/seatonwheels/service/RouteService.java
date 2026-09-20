package com.vatsalya.seatonwheels.service;



import com.vatsalya.seatonwheels.entity.Route;
import com.vatsalya.seatonwheels.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }


    public List<Route> getAllRoutes()
    {
        return routeRepository.findAll();

    }
}
