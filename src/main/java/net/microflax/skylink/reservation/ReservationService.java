package net.microflax.skylink.reservation;

import net.microflax.skylink.AbstractService;
import net.microflax.skylink.flight.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservationService extends AbstractService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationSimulator reservationSimulator;

    public void persistReservation(Reservation reservation) {
        reservation.setCreatedAt(LocalDateTime.now());
        reservationRepository.save(reservation);
    }

    @Override
    public void generate() {
        reservationSimulator.run();
    }

    /**
     * Update the number of available seats in the flight
     *
     * @param id the id of the reservation
     */
    public void updateAvailableSeats(int id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()) throw new NoSuchElementException("The reservation is not in the database");
        if (reservation.get().getFlight().getAvailableSeats() == 0) throw new UnsupportedOperationException(
                "There are no more available seats on the flight");
        reservation.get().getFlight().setAvailableSeats(reservation.get().getFlight().getAvailableSeats() - 1);
        reservationRepository.save(reservation.get());
    }
}
