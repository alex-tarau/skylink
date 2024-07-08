package net.microflax.skylink.passenger;

import com.github.javafaker.*;
import net.microflax.skylink.AbstractService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.random.RandomGenerator;

@Service
public class PassengerService extends AbstractService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassportRepository passportRepository;

    @Autowired
    private PassengerSimulator passengerSimulator;

    /**
     * Persist the passenger into the database
     * @param passenger the passenger
     * @param birthDate the date of birth of the passenger in the yyyy-MM-dd format
     */
    public void persistPassenger(Passenger passenger, String birthDate) {
        passengerRepository.save(passenger);
        Passport passport = new Passport();
        passport.setPassenger(passenger);
        passport.setCreatedAt(LocalDateTime.now());
        passport.setExpirationDate(passport.getCreatedAt().plusYears(10));
        passport.setFirstName(passenger.getFirstName());
        passport.setLastName(passenger.getLastName());
        passport.setBirthDate(LocalDate.parse(birthDate));
        passport.setPassport_number(generatePassportNumber());
        passportRepository.save(passport);
    }

    @Override
    public void generate() {
        passengerSimulator.run();
    }

    /**
     * Renew the passport
     *
     * @param id the id of the passport
     */
    public void renewPassport(int id) {
        Passport newPassport = passportRepository.findById(id).orElseThrow();
        if (Math.abs(LocalDateTime.now().getYear() - newPassport.getCreatedAt().getYear()) > 15) {
            newPassport.setModifiedAt(LocalDateTime.now());
            newPassport.setExpirationDate(newPassport.getModifiedAt().plusYears(10));
            passportRepository.save(newPassport);
        } else {
            throw new UnsupportedOperationException("Passport can not be renew because it was issue more than 15 " +
                    "years ago");
        }
    }


    private String generatePassportNumber() {
        return RandomStringUtils.random(1, true, false) + RandomStringUtils.random(8, false
                , true).toUpperCase();
    }

}
