package net.microflax.skylink.simulator;

import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airline.AirlineRepository;
import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airplane.AirplaneRepository;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.airport.AirportRepository;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.flight.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Component
public class FlightSimulator extends AbstractSimulator<Flight> {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Override
    protected Flight simulate() {
        Flight flight = new Flight();
        flight.setCreatedAt(LocalDateTime.now());
        flight.setAirplane(createAirplane());
        flight.setDaysOfWeek(EnumSet.allOf(DayOfWeek.class));
        flight.setArrival(getFaker().timeAndDate().past(10, TimeUnit.DAYS).atZone(ZoneId.systemDefault()).
                toLocalTime());
        flight.setDeparture(getFaker().timeAndDate().future(1, TimeUnit.DAYS).atZone(ZoneId.systemDefault()).
                toLocalTime());
        flight.setOriginAirport(createAirport());
        flight.setDestinationAirport(createAirport());
        flight.setAirline(createAirline());
        flight.setName(getFaker().aviation().flight());
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

    private Airplane createAirplane(){
        Airplane airplane= new Airplane();
        airplane.setName(getFaker().aviation().airplane());
        airplane.setSerialNumber(getFaker().idNumber().valid().substring(0,10));
        airplane.setDeliveryDate(LocalDate.now());
        airplane.setManufacturer(getFaker().aviation().manufacturer());
        airplane.setModel(getFaker().aviation().airplane());
        airplane.setModelYear(ThreadLocalRandom.current().nextInt(2000,2024));
        airplane.setEconomySeats(getFaker().number().numberBetween(200, 851));
        airplane.setEconomyPlusSeats(getFaker().number().numberBetween(200, 851));
        airplane.setBusinessSeats(getFaker().number().numberBetween(200, 851));
        airplane.setFirstClassSeats(getFaker().number().numberBetween(200, 851));
        airplaneRepository.save(airplane);
        return airplane;
    }
}
