package com.vatsalya.seatonwheels.service;


import com.vatsalya.seatonwheels.entity.Bus;
import com.vatsalya.seatonwheels.repository.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    private final BusRepository busRepository;

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }
    public List<Bus> getAllBuses()
    {
        return busRepository.findAll();
    }

    public Bus getBusById(Long id)
    {
        return busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus Not Found"));
    }
}

