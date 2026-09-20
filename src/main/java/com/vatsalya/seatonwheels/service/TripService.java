package com.vatsalya.seatonwheels.service;


import com.vatsalya.seatonwheels.entity.Trip;
import com.vatsalya.seatonwheels.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository)
    {
        this.tripRepository=tripRepository;
    }

    public List<Trip> getAllTrips()
    {
        return tripRepository.findAll();
    }
}
