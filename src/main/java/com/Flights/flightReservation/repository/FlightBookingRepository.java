package com.Flights.flightReservation.repository;

import com.Flights.flightReservation.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking,Long> {

    List<FlightBooking> findByUserId(Long userId);


}
