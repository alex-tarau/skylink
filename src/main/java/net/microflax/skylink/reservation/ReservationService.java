package net.microflax.skylink.reservation;

import net.microflax.skylink.AbstractService;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.flight.FlightRepository;
import net.microflax.skylink.simulator.ReservationSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservationService extends AbstractService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    public void persistReservation(Reservation reservation) {
        reservation.setCreatedAt(LocalDateTime.now());
        updateAvailableSeats(reservation.getId());
        reservationRepository.save(reservation);
    }

    /**
     * Update the number of available seats in the flight
     *
     * @param id the id of the reservation
     */
    private void updateAvailableSeats(int id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()) throw new NoSuchElementException("The reservation is not in the database");
        Flight flight = reservation.get().getFlight();
        if (flight.getAvailableSeats() == 0) throw new UnsupportedOperationException(
                "There are no more available seats on the flight");
        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepository.save(flight);
    }
}
