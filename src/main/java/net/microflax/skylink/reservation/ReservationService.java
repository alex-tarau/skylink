package net.microflax.skylink.reservation;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}
