package net.microflax.skylink.service;

import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import net.microflax.skylink.jpa.Airline;
import net.microflax.skylink.jpa.AirlineRepository;
import net.microflax.skylink.jpa.Airport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirlineServiceTest {

    @Mock
    private Faker faker;

    @Mock
    private PhoneNumber phoneNumber;

    @Mock
    private Country country;

    private Airline airline;

    @Mock
    private AirlineRepository airlineRepository;

    @InjectMocks
    private AirlineService airlineService;

    @BeforeEach
    void setUp() {
        airline = new Airline();
        when(airlineRepository.save(airline)).thenReturn(airline);
        when(airlineRepository.save(airline)).thenReturn(airline);
        when(airlineRepository.save(airline)).thenReturn(airline);
        when(airlineRepository.save(airline)).thenReturn(airline);
        when(airlineRepository.save(airline)).thenReturn(airline);
        when(airlineRepository.save(airline)).thenReturn(airline);
    }


    @Test
    void persist() {
        airlineService.persist();
        verify(airlineRepository,times(6)).save(airline);
    }
}