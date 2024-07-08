package net.microflax.skylink.service;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Number;
import com.github.javafaker.PhoneNumber;
import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airline.AirlineRepository;
import net.microflax.skylink.airline.AirlineService;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.airport.AirportRepository;
import net.microflax.skylink.airport.AirportService;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.flight.FlightRepository;
import net.microflax.skylink.flight.FlightService;
import net.microflax.skylink.airport.LocationRepository;
import net.microflax.skylink.flight.FlightSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

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
        when(flightRepository.save(flight)).thenReturn(flight);
    }

    @Test
    void persist() {
        flightService.persistFlight(flight);
        verify(flightRepository).save(flight);
    }
}