package net.microflax.skylink.passenger;

import net.microflax.skylink.AbstractService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PassengerService extends AbstractService<Passenger> {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public void persist(Passenger passenger) {
        passenger.setPassport_number(generatePassportNumber());
        passenger.setCreatedAt(LocalDateTime.now());
        passengerRepository.save(passenger);
    }

    private String generatePassportNumber() {
        return RandomStringUtils.random(1, true, false).toUpperCase() +
                RandomStringUtils.random(8, false, true).toUpperCase();
    }
}
