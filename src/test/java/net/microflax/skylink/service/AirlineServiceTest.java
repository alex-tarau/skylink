package net.microflax.skylink.service;

import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airline.AirlineRepository;
import net.microflax.skylink.airline.AirlineService;
import net.microflax.skylink.airline.AirlineSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirlineServiceTest {


    private Airline airline;

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private AirlineSimulator airlineSimulator;

    @InjectMocks
    private AirlineService airlineService;

    @BeforeEach
    void setUp() {
        airline = new Airline();
        when(airlineRepository.save(airline)).thenReturn(airline);
    }


    @Test
    void persist() {
        airlineService.persistAirline(airline);
        verify(airlineRepository).save(airline);
    }
}