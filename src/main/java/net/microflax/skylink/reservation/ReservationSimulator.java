package net.microflax.skylink.reservation;

import net.microflax.skylink.AbstractSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationSimulator extends AbstractSimulator {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    protected void simulate() {
        reservationRepository.save(createReservation());
    }
}
