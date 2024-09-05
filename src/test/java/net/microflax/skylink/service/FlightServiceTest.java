package net.microflax.skylink.service;

import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.flight.FlightRepository;
import net.microflax.skylink.flight.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

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
    }
}