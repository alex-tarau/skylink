package net.microflax.skylink.service;

import com.github.javafaker.*;
import com.github.javafaker.Number;
import net.microflax.skylink.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class PassengerService extends AbstractService<Passenger> {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private PassportRepository passportRepository;

    private final Name name = getFaker().name();
    private final Internet internet = getFaker().internet();


    @Override
    public void persist(Passenger passenger) {

    }

    @Override
    public void generate() {
        int numberOfPassengers = 0;
        while (numberOfPassengers < NUMBER_OF_ENTITIES_TO_PERSIST) {
            Passenger passenger = createPassenger(new Passenger());
            passengerRepository.save(passenger);
            Passport passport= createPassport(passenger);
            passportRepository.save(passport);
            numberOfPassengers++;
        }
    }

    public void renewPassport(int id) {
        Passport newPassport = passportRepository.findById(id).get();
        if ((LocalDateTime.now().getYear() - newPassport.getCreatedAt().getYear()) <= 15) {
            newPassport.setModifiedAt(LocalDateTime.now());
            newPassport.setExpirationDate(newPassport.getModifiedAt().plusYears(10));
            passportRepository.save(newPassport);
        } else {
            throw new UnsupportedOperationException("Passport can not be renew because it was issue more than 15 " +
                    "years ago");
        }
    }


    private Passenger createPassenger(Passenger passenger) {
        passenger.setFirstName(name.firstName());
        passenger.setLastName(name.lastName());
        passenger.setEmail(internet.emailAddress());
        return passenger;
    }

    private Passport createPassport(Passenger passenger) {
        Passport passport= new Passport();
        passport.setPassenger(passenger);
        passport.setCreatedAt(LocalDateTime.now());
        passport.setFirstName(passenger.getFirstName());
        passport.setLastName(passenger.getLastName());
        passport.setBirthDate(getDateAndTime().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        passport.setPassport_number(generatePassportNumber());
        passport.setExpirationDate(passport.getCreatedAt().plusYears(10));
        return passport;
    }

    private String generatePassportNumber() {
            return getFaker().bothify("?"+"#".repeat(7),true);
    }

}
