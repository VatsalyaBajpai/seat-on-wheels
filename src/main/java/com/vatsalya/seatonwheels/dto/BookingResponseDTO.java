package com.vatsalya.seatonwheels.dto;


import com.vatsalya.seatonwheels.entity.BookingStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
@Builder
public class BookingResponseDTO {
    private long bookingId;
    private long userId;
    private String userName;
    private String busNumber;
    private String seatNumber;
    private String source;
    private String destination;
    private LocalDateTime departureTime;
    private double fare;
    private LocalDateTime bookingTime;
    private BookingStatus status;

}
