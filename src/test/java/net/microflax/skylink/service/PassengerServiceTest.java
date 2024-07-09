package net.microflax.skylink.service;

import com.github.javafaker.*;
import net.microflax.skylink.passenger.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    private PassengerSimulator passengerSimulator;


    @InjectMocks
    private PassengerService passengerService;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
        passport = new Passport();
        passport.setPassenger(passenger);
        passport.setCreatedAt(LocalDateTime.now());
        when(passengerRepository.save(passenger)).thenReturn(passenger);
        when(passportRepository.save(passport)).thenReturn(passport);
    }


    @Test
    void persist() {
        passengerService.persistPassenger(passenger, "2017-06-01");
        verify(passengerRepository).save(passenger);
        verify(passportRepository).save(passport);
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void renewPassportPersistInTheDataBase() {
        when(passportRepository.findById(anyInt())).thenReturn(Optional.of(passport));
        passengerService.renewPassport(1);
        verify(passportRepository).save(passport);
    }


    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void renewPassportNotPersistInTheDataBase() {
        when(passportRepository.findById(anyInt())).thenReturn(Optional.empty());
        Assertions.assertThrows(NoSuchElementException.class,()->passengerService.renewPassport(0));
    }
}