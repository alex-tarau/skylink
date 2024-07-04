package net.microflax.skylink.service;

import com.github.javafaker.*;
import net.microflax.skylink.jpa.Passenger;
import net.microflax.skylink.jpa.PassengerRepository;
import net.microflax.skylink.jpa.Passport;
import net.microflax.skylink.jpa.PassportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private PassportRepository passportRepository;

    private Passenger passenger;
    private Passport passport;

    @Mock
    private Faker faker;

    @Mock
    private Name name;

    @Mock
    private Internet internet;

    @Mock
    private DateAndTime dateAndTime;

    @Mock
    private Lorem lorem;

    @InjectMocks
    private PassengerService passengerService;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
        passport = new Passport();
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        when(passportRepository.save(passport)).thenReturn(passport);
        when(passportRepository.save(passport)).thenReturn(passport);
        when(passportRepository.save(passport)).thenReturn(passport);
        when(passportRepository.save(passport)).thenReturn(passport);
        when(passportRepository.save(passport)).thenReturn(passport);
        when(passportRepository.save(passport)).thenReturn(passport);
    }


    @Test
    void generate() {
        passengerService.generate();
        verify(passengerRepository,times(6)).save(passenger);
        verify(passportRepository,times(6)).save(passport);
    }

    @Test
    void renewPassport() {
    }
}