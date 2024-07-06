package net.microflax.skylink.passenger;

import net.microflax.skylink.AbstractSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PassengerSimulator extends AbstractSimulator {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassportRepository passportRepository;

    @Override
    protected void simulate() {
        Passenger passenger = createPassenger(new Passenger());
        passengerRepository.save(passenger);
        passportRepository.save(createPassport(passenger));
    }
}
