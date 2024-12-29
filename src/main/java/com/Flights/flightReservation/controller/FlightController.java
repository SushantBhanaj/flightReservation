package com.Flights.flightReservation.controller;

import com.Flights.flightReservation.Service.FlightService;
import com.Flights.flightReservation.entity.FlightBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/book/{userId}")
    public ResponseEntity<String> bookFlight(@PathVariable Long userId, @RequestBody FlightBooking flightBooking) {
        boolean isBooked = flightService.bookFlight(userId, flightBooking);
        return isBooked
                ? ResponseEntity.status(HttpStatus.CREATED).body("Booking successful")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Booking failed");
    }

    @GetMapping("/user/{userId}")
    public List<FlightBooking> getUserBookings(@PathVariable Long userId) {
        return flightService.getUserBookings(userId);
    }

    @GetMapping("/all")
    public List<FlightBooking> getAllBookings() {
        return flightService.getAllBookings();
    }

    @GetMapping("/{id}")
    public FlightBooking getBookingById(@PathVariable Long id) {
        return flightService.getBookingById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable Long id) {
        flightService.deleteBooking(id);
        return "Booking deleted";
    }

    @PutMapping("/{id}")
    public FlightBooking updateBooking(@PathVariable Long id, @RequestBody FlightBooking updatedBooking) {
        return flightService.updateBooking(id, updatedBooking);
    }
}


