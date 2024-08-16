package net.microflax.skylink.reservation;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService extends AbstractService<Reservation> {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void persist(Reservation reservation) {
        reservation.setCreatedAt(LocalDateTime.now());
        reservationRepository.save(reservation);
    }
}
