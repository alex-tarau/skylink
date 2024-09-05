package net.microflax.skylink.service;

import net.microflax.skylink.passenger.PassengerRepository;
import net.microflax.skylink.passenger.PassengerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerService passengerService;

    @Test
    void persist() {
        assertTrue(Character.isAlphabetic(passengerService.generatePassportNumber().charAt(0)));
        assertEquals(9, passengerService.generatePassportNumber().length());
    }
}