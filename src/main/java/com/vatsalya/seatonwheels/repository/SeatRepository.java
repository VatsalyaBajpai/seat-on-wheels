package com.vatsalya.seatonwheels.repository;


import com.vatsalya.seatonwheels.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByBusId(Long busId);//ex find all seats , then here findall seats but by busId//

}
