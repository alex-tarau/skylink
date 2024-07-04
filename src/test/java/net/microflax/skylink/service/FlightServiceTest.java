package net.microflax.skylink.service;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Number;
import com.github.javafaker.PhoneNumber;
import net.microflax.skylink.jpa.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private Faker faker;

    @Mock
    private Address address;

    @Mock
    private AirportService airportService;

    @Mock
    private AirlineRepository airlineRepository;

    @Mock
    private PhoneNumber phoneNumber;

    @Mock
    private AirlineService airlineService;

    private Airport airport;
    private Airline airline;
    private Flight flight;

    @Mock
    private Number number;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        airport = new Airport();
        airline = new Airline();
        airline.setName("Air Canada");
        flight = new Flight();
        when(airportService.createAirport()).thenReturn(airport);
        when(airlineService.createAirline()).thenReturn(airline);
    }

    @Test
    void generate() {
        flightService.generate();
        verify(flightRepository,times(6)).save(flight);
    }
}