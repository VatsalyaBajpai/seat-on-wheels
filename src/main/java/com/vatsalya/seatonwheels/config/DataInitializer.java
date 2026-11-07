package com.vatsalya.seatonwheels.config;

import com.vatsalya.seatonwheels.entity.*;
import com.vatsalya.seatonwheels.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final SeatRepository seatRepository;
    private final TripRepository tripRepository;
    private final TripSeatRepository tripSeatRepository;
    public DataInitializer(
            UserRepository userRepository,
            BusRepository busRepository,
            RouteRepository routeRepository,
            SeatRepository seatRepository,
            TripRepository tripRepository,
            TripSeatRepository tripSeatRepository
    ) {
        this.userRepository = userRepository;
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
        this.seatRepository = seatRepository;
        this.tripRepository = tripRepository;
        this.tripSeatRepository = tripSeatRepository;
    }
    @Override
    public void run(String... args) {
        // Prevent duplicate seeding if data already exists
        if (userRepository.count() > 0) {
            return;
        }
        // 1. Create a sample User
        User user = new User();
        user.setName("Vatsalya Bajpai");
        user.setEmail("vatsalya@example.com");
        user.setPassword("password123");
        user.setRole("ROLE_USER");
        userRepository.save(user);
        // 2. Create a Bus
        Bus bus = new Bus();
        bus.setBusNumber("UP32 AB 1234");
        bus.setBusType("AC Sleeper (2+1)");
        bus = busRepository.save(bus);
        // 3. Create physical Seats for the Bus
        Seat seat1 = new Seat(0L, "1A", "SLEEPER", bus);
        Seat seat2 = new Seat(0L, "1B", "SLEEPER", bus);
        Seat seat3 = new Seat(0L, "2A", "SLEEPER", bus);
        Seat seat4 = new Seat(0L, "2B", "SLEEPER", bus);
        seatRepository.saveAll(List.of(seat1, seat2, seat3, seat4));
        // 4. Create a Route
        Route route = new Route();
        route.setSource("Lucknow");
        route.setDestination("Delhi");
        route = routeRepository.save(route);
        // 5. Create a Trip
        Trip trip = new Trip();
        trip.setBus(bus);
        trip.setRoute(route);
        trip.setDepartureTime(LocalDateTime.now().plusDays(1).withHour(21).withMinute(0));
        trip.setArrivalTime(LocalDateTime.now().plusDays(2).withHour(6).withMinute(0));
        trip.setFare(1200.0);
        trip = tripRepository.save(trip);
        // 6. Create TripSeats (Trip-specific Inventory)
        TripSeat ts1 = new TripSeat(null, trip, seat1, SeatStatus.AVAILABLE);
        TripSeat ts2 = new TripSeat(null, trip, seat2, SeatStatus.AVAILABLE);
        TripSeat ts3 = new TripSeat(null, trip, seat3, SeatStatus.AVAILABLE);
        TripSeat ts4 = new TripSeat(null, trip, seat4, SeatStatus.AVAILABLE);
        tripSeatRepository.saveAll(List.of(ts1, ts2, ts3, ts4));
        System.out.println(">>> Sample test data successfully initialized! <<<");
    }
}