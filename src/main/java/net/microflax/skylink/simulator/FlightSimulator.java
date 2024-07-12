package net.microflax.skylink.simulator;

import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airline.AirlineRepository;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.airport.AirportRepository;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.flight.FlightRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class FlightSimulator extends AbstractSimulator<Flight> {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    protected Flight simulate() {
        Flight flight = new Flight();
        flight.setCreatedAt(LocalDateTime.now());
        flight.setArrival(getFaker().timeAndDate().past(10, TimeUnit.DAYS).atZone(ZoneId.systemDefault()).
                toLocalDateTime());
        flight.setDeparture(getFaker().timeAndDate().future(1, TimeUnit.DAYS).atZone(ZoneId.systemDefault()).
                toLocalDateTime());
        flight.setAvailableSeats(getFaker().number().numberBetween(200, 851));
        flight.setOriginAirport(createAirport());
        flight.setDestinationAirport(createAirport());
        flight.setAirline(createAirline());
        flight.setFlightNumber(generateFlightNumber(flight.getAirline().getName()));
        return flight;
    }

    @Override
    protected void save(Flight flight) {
        flightRepository.save(flight);
    }

    private Airline createAirline() {
        Airline airline = new Airline();
        airline.setContactNumber(getFaker().phoneNumber().phoneNumber());
        airline.setName(getFaker().aviation().airline());
        airline.setOperatingRegion(getFaker().country().name());
        airlineRepository.save(airline);
        return airline;
    }

    private Airport createAirport() {
        Airport airport = new Airport();
        airport.setAirportCode(getFaker().aviation().airport().substring(0, 3));
        airport.setName(getFaker().aviation().airportName());
        airport.setStreet(getFaker().address().streetName());
        airport.setCity(getFaker().address().cityName());
        airport.setCountry(getFaker().country().name());
        airportRepository.save(airport);
        return airport;
    }

    private String generateFlightNumber(String airline) {
        long digits = getFaker().number().randomNumber(4, false);
        airline = airline.toUpperCase();
        List<String> data = Arrays.asList(airline.split(" "));
        if (data.size()==1) return data.get(0);
        data.removeIf(String::isEmpty);
        return data.get(0).charAt(0) + String.valueOf(data.get(1).charAt(0)) + digits;
    }
}
