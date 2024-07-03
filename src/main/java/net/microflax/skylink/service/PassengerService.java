package net.microflax.skylink.service;

import com.github.javafaker.*;
import net.microflax.skylink.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final DateAndTime dateAndTime = getFaker().date();
    private final Lorem lorem = getFaker().lorem();


    @Override
    public void persist(Passenger passenger) {
        Passenger jpaPassenger = createPassenger(passenger);
        passengerRepository.save(jpaPassenger);
        Passport jpaPassport = createPassport(jpaPassenger);
        passportRepository.save(jpaPassport);
    }

    @Override
    public void generate() {

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
        passport.setBirthDate(LocalDate.parse(dateAndTime.birthday().toString()));
        passport.setPassport_number(generatePassportNumber());
        passport.setExpirationDate(passport.getCreatedAt().plusYears(10));
        return passport;
    }

    private String generatePassportNumber() {
        String passportNumber = lorem.characters(9, true, true).toUpperCase();
        char firstCharacter = passportNumber.charAt(0);
        if (firstCharacter == 'A' || firstCharacter == 'I' || firstCharacter == 'E' || firstCharacter == 'O' ||
                firstCharacter == 'U') {
            return passportNumber;
        } else {
            char[] vowels = {'A', 'I', 'E', 'O', 'U'};
            return vowels[new Random().nextInt(vowels.length)] + lorem.characters(8,
                    true, true).toUpperCase();
        }
    }

}
