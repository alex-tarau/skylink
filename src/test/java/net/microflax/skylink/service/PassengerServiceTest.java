package net.microflax.skylink.service;

import net.microflax.skylink.passenger.*;
import net.microflax.skylink.simulator.PassengerSimulator;
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

    private Passenger passenger;

    @InjectMocks
    private PassengerService passengerService;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
        when(passengerRepository.save(passenger)).thenReturn(passenger);
    }


    @Test
    void persist() {
        passengerService.persistPassenger(passenger, "2017-06-01");
        verify(passengerRepository).save(passenger);
    }
}