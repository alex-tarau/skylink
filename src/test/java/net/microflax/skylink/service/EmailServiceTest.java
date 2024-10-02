package net.microflax.skylink.service;

import jakarta.mail.MessagingException;
import net.microflax.skylink.EmailService;
import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.payment.Payment;
import net.microflax.skylink.payment.PaymentRepository;
import net.microflax.skylink.reservation.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private EmailService emailService;

    private Payment payment;

    @BeforeEach
    void setUp() {
        Flight flight = createFlight();
        Passenger passenger = createPassenger();
        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        payment = new Payment();
        payment.setId(1);
        payment.setReservation(reservation);
        payment.setMethod(Payment.Method.VISA);
        payment.setAmount(BigDecimal.valueOf(100));
        payment.setCreditCardNumber("378282246310005");
    }

    @Test
    void didNotSendConfirmationEmail() {
        when(paymentRepository.findById(anyInt())).thenReturn(Optional.of(payment));
        assertThrows(IllegalArgumentException.class,() -> emailService.sendConfirmationEmail(1));
        verify(mailSender,never()).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendConfirmationEmail() throws MessagingException {
        payment.setStatus(Payment.Status.SUCCESS);
        when(paymentRepository.findById(anyInt())).thenReturn(Optional.of(payment));
        emailService.sendConfirmationEmail(1);
        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    private Passenger createPassenger() {
        Passenger passenger = new Passenger();
        passenger.setEmail("change.me@gmail.com");
        passenger.setFirstName("Bob");
        passenger.setLastName("Smith");
        return passenger;
    }

    private Flight createFlight() {
        Airplane airplane = new Airplane();
        airplane.setName("Airplane");
        Airport origin = new Airport();
        origin.setCity("New York");
        Airport destination = new Airport();
        destination.setCity("Paris");
        Airline airline = new Airline();
        airline.setName("United Airlines");
        Flight flight = new Flight();
        flight.setAirplane(airplane);
        flight.setAirline(airline);
        flight.setOriginAirport(origin);
        flight.setDestinationAirport(destination);
        flight.setDeparture(LocalTime.of(12, 5));
        flight.setArrival(LocalTime.MIDNIGHT);
        flight.setName("1234");
        return flight;
    }
}