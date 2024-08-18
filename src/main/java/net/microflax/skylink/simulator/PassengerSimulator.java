package net.microflax.skylink.simulator;

import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.passenger.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PassengerSimulator extends AbstractSimulator<Passenger> {

    @Autowired
    private PassengerRepository passengerRepository;


    @Override
    protected Passenger simulate() {
        Passenger passenger = new Passenger();
        passenger.setFirstName(getFaker().name().firstName());
        passenger.setLastName(getFaker().name().lastName());
        passenger.setEmail(getFaker().internet().emailAddress());
        passenger.setCreatedAt(LocalDateTime.now());
        passenger.setBirthDate(getFaker().timeAndDate().birthday());
        passenger.setPassport_number(getFaker().passport().valid());
        return passenger;
    }


    @Override
    protected void save(Passenger passenger) {
        passengerRepository.save(passenger);
    }
}
