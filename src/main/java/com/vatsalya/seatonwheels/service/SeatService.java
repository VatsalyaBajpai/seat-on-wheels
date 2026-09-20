package com.vatsalya.seatonwheels.service;


import com.vatsalya.seatonwheels.entity.Seat;
import com.vatsalya.seatonwheels.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository)
    {
        this.seatRepository=seatRepository;
    }

    public List<Seat> getAllSeats()
    {
        return seatRepository.findAll();
    }

    public List<Seat> getSeatsByBus(Long busId)
    {
        return seatRepository.findByBusId(busId);
    }
}
