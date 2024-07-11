package net.microflax.skylink.passenger;

import net.microflax.skylink.AbstractService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PassengerService extends AbstractService {

    @Autowired
    private PassengerRepository passengerRepository;

    /**
     * Persist the passenger with its date of birth into the database
     *
     * @param passenger the passenger
     * @param birthDate the date of birth of the passenger
     */
    public void persistPassenger(Passenger passenger,String birthDate) {
        passenger.setPassport_number(generatePassportNumber());
        passenger.setCreatedAt(LocalDateTime.now());
        passenger.setBirthDate(LocalDate.parse(birthDate));
        passengerRepository.save(passenger);
    }


    private String generatePassportNumber() {
        return RandomStringUtils.random(1, true, false).toUpperCase() +
                RandomStringUtils.random(8, false, true).toUpperCase();
    }

}
