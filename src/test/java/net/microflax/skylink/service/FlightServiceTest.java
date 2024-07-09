package net.microflax.skylink.service;

import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.flight.FlightRepository;
import net.microflax.skylink.flight.FlightService;
import net.microflax.skylink.flight.FlightSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightSimulator flightSimulator;

    private Airport orginAirport;
    private Airport destinationAirport;
    private Airline airline;
    private Flight flight;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        orginAirport = new Airport();
        orginAirport.setName("Newark International Airport");
        destinationAirport= new Airport();
        destinationAirport.setName("Munich International Airport");
        airline = new Airline();
        airline.setName("Air Canada");
        flight = new Flight();
        flight.setOriginAirport(orginAirport);
        flight.setDestinationAirport(destinationAirport);
        flight.setAirline(airline);
        flight.setAvailableSeats(ThreadLocalRandom.current().nextInt(200,851));
        when(flightRepository.save(flight)).thenReturn(flight);
    }

    @Test
    void persist() {
        flightService.persistFlight(flight);
        verify(flightRepository).save(flight);
    }
}