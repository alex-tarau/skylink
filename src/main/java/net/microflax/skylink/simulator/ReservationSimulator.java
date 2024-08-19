package net.microflax.skylink.simulator;

import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airline.AirlineRepository;
import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.airport.AirportRepository;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.flight.FlightRepository;
import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.passenger.PassengerRepository;
import net.microflax.skylink.reservation.Reservation;
import net.microflax.skylink.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Component
public class ReservationSimulator extends AbstractSimulator<Reservation> {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    @Override
    protected Reservation simulate() {
        Reservation reservation = new Reservation();
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setPassenger(createPassenger());
        reservation.setFlight(createFlight());
        reservation.setSeat(Reservation.Seat.ECONOMY);
        return reservation;
    }

    @Override
    protected void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    private Flight createFlight() {
        Flight flight = new Flight();
        flight.setCreatedAt(LocalDateTime.now());
        flight.setName(getFaker().aviation().flight());
        flight.setArrival(getFaker().timeAndDate().past(10, TimeUnit.DAYS).atZone(ZoneId.systemDefault()).
                toLocalDateTime());
        flight.setDeparture(getFaker().timeAndDate().future(10, TimeUnit.DAYS).atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        flight.setOriginAirport(createAirport());
        flight.setDestinationAirport(createAirport());
        flight.setAirline(createAirline());
        flight.setAirplane(createAirplane());
        flightRepository.save(flight);
        return flight;
    }

    private Passenger createPassenger() {
        Passenger passenger = new Passenger();
        passenger.setBirthDate(getFaker().timeAndDate().birthday());
        passenger.setPassport_number(getFaker().passport().valid());
        passenger.setEmail(getFaker().internet().emailAddress());
        passenger.setFirstName(getFaker().name().firstName());
        passenger.setLastName(getFaker().name().lastName());
        passenger.setCreatedAt(LocalDateTime.now());
        passengerRepository.save(passenger);
        return passenger;
    }

    private Airport createAirport() {
        Airport airport = new Airport();
        airport.setCity(getFaker().address().cityName());
        airport.setName(getFaker().aviation().airportName());
        airport.setAirportCode(getFaker().aviation().airport().substring(0, 2));
        airport.setStreet(getFaker().address().streetName());
        airport.setCountry(getFaker().country().name());
        airportRepository.save(airport);
        return airport;
    }

    private Airline createAirline() {
        Airline airline = new Airline();
        airline.setOperatingRegion(getFaker().country().name());
        airline.setContactNumber(getFaker().phoneNumber().phoneNumber());
        airline.setName(getFaker().aviation().airline());
        airlineRepository.save(airline);
        return airline;
    }

    private Airplane createAirplane(){
        Airplane airplane= new Airplane();
        airplane.setSerialNumber(getFaker().idNumber().valid().substring(0,10));
        airplane.setManufacturer(getFaker().company().name());
        airplane.setDeliveryDate(LocalDate.now());
        airplane.setModel(getFaker().aviation().airplane());
        airplane.setModelYear(ThreadLocalRandom.current().nextInt(2000,2024));
        airplane.setEconomySeats(getFaker().number().numberBetween(200, 851));
        airplane.setEconomyPlusSeats(getFaker().number().numberBetween(200, 851));
        airplane.setBusinessSeats(getFaker().number().numberBetween(200, 851));
        airplane.setFirstClassSeats(getFaker().number().numberBetween(200, 851));
        return airplane;
    }
}
