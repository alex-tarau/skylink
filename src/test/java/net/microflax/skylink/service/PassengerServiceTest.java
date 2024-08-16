package net.microflax.skylink.service;

import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.passenger.PassengerRepository;
import net.microflax.skylink.passenger.PassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        passengerService.persist(passenger);
        verify(passengerRepository).save(passenger);
    }
}