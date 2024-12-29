package com.Flights.flightReservation.Service.Impl;

import com.Flights.flightReservation.Service.FlightService;
import com.Flights.flightReservation.entity.FlightBooking;
import com.Flights.flightReservation.entity.User;
import com.Flights.flightReservation.repository.FlightBookingRepository;
import com.Flights.flightReservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightBookingRepository flightBookingRepository;

    @Autowired
    private UserRepository userRepository;

    /*@Override
    public boolean bookFlight(Long userId, FlightBooking flightBooking) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            flightBooking.setUser(user);
            flightBookingRepository.save(flightBooking);
            return true;
        }
        return false;
    }*/
    @Override
       public boolean bookFlight(Long userId, FlightBooking flightBooking) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            Optional<FlightBooking> flightOpt = flightBookingRepository.findById(flightBooking.getId());
            if (flightOpt.isPresent()) {
                FlightBooking flight = flightOpt.get();
                if (flight.getAvailableSeats() >= flightBooking.getAvailableSeats()) {
                    flight.setAvailableSeats(flight.getAvailableSeats() - flightBooking.getAvailableSeats());
                    flight.setUser(user);
                    flightBookingRepository.save(flight);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<FlightBooking> getUserBookings(Long userId) {
        return flightBookingRepository.findByUserId(userId);
    }

    @Override
    public List<FlightBooking> getAllBookings() {
        return flightBookingRepository.findAll();
    }

    @Override
    public FlightBooking getBookingById(Long id) {
        return flightBookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public void deleteBooking(Long id) {
        flightBookingRepository.deleteById(id);
    }

    @Override
    public FlightBooking updateBooking(Long id, FlightBooking updatedBooking) {
        FlightBooking existingBooking = getBookingById(id);
        existingBooking.setFlightNumber(updatedBooking.getFlightNumber());
        existingBooking.setSource(updatedBooking.getSource());
        existingBooking.setDestination(updatedBooking.getDestination());
        existingBooking.setDepartureTime(updatedBooking.getDepartureTime());
        existingBooking.setArrivalTime(updatedBooking.getArrivalTime());
        existingBooking.setAvailableSeats(updatedBooking.getAvailableSeats());
        existingBooking.setPrice(updatedBooking.getPrice());
        return flightBookingRepository.save(existingBooking);
    }
}


