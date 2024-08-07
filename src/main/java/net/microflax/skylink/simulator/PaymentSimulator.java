package net.microflax.skylink.simulator;

import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airline.AirlineRepository;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.airport.AirportRepository;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.flight.FlightRepository;
import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.passenger.PassengerRepository;
import net.microflax.skylink.payment.Payment;
import net.microflax.skylink.payment.PaymentRepository;
import net.microflax.skylink.reservation.Reservation;
import net.microflax.skylink.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Component
public class PaymentSimulator extends AbstractSimulator<Payment> {


    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    protected Payment simulate() {
        Payment payment = new Payment();
        payment.setCreatedAt(LocalDateTime.now());
        payment.setAmount((float) getFaker().number().randomDouble(2, 300, 500));
        payment.setMethod(Payment.Method.VISA);
        payment.setReservation(createReservation());
        payment.setSentAt(LocalDateTime.now());
        payment.setStatus(Payment.Status.SUCCESS);
        return payment;
    }


    @Override
    protected void save(Payment payment) {
        paymentRepository.save(payment);
    }

    private Flight createFlight() {
        Flight flight = new Flight();
        flight.setCreatedAt(LocalDateTime.now());
        flight.setAvailableSeats(getFaker().number().numberBetween(200, 851));
        flight.setFlightNumber(getFaker().bothify("?" + "#".repeat(8), true));
        flight.setArrival(getFaker().timeAndDate().past(10, TimeUnit.DAYS).atZone(ZoneId.systemDefault()).
                toLocalDateTime());
        flight.setDeparture(getFaker().timeAndDate().future(10, TimeUnit.DAYS).atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        flight.setOriginAirport(createAirport());
        flight.setDestinationAirport(createAirport());
        flight.setAirline(createAirline());
        flightRepository.save(flight);
        return flight;
    }

    private Passenger createPassenger() {
        Passenger passenger = new Passenger();
        passenger.setBirthDate(getFaker().timeAndDate().birthday());
        passenger.setPassport_number(getFaker().bothify("?" + "#".repeat(8), true));
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

    private Reservation createReservation(){
        Reservation reservation= new Reservation();
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setFlight(createFlight());
        reservation.setPassenger(createPassenger());
        reservationRepository.save(reservation);
        return reservation;
    }
}
