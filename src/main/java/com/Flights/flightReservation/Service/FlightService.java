package com.Flights.flightReservation.Service;

import com.Flights.flightReservation.entity.FlightBooking;

import java.util.List;

public interface FlightService {
    boolean bookFlight(Long userId, FlightBooking flightBooking);
    List<FlightBooking> getUserBookings(Long userId);
    List<FlightBooking> getAllBookings();
    FlightBooking getBookingById(Long id);
    void deleteBooking(Long id);
    FlightBooking updateBooking(Long id, FlightBooking updatedBooking);

}
