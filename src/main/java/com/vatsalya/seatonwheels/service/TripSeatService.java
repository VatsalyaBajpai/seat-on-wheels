package com.vatsalya.seatonwheels.service;


import com.vatsalya.seatonwheels.entity.TripSeat;
import com.vatsalya.seatonwheels.repository.TripSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripSeatService
{

    private final TripSeatRepository tripSeatRepository;

    public TripSeatService(TripSeatRepository tripSeatRepository)
    {
        this.tripSeatRepository = tripSeatRepository;
    }

    public List<TripSeat> getAllTripSeats()
    {
        return tripSeatRepository.findAll();
    }
}

