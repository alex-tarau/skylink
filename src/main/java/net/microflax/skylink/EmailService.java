package net.microflax.skylink;

import lombok.extern.slf4j.Slf4j;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.payment.Payment;
import net.microflax.skylink.payment.PaymentRepository;
import net.microflax.skylink.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Send a confirmation email for the passenger's flight reservation
     *
     * @param id the id of the passenger's payment for the flight
     */
    @Transactional
    public void sendConfirmationEmail(int id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            if (optionalPayment.get().getStatus() != Payment.Status.SUCCESS)
                throw new IllegalArgumentException("The payment was not successful");
        } else {
            throw new NoSuchElementException("The payment is not persisted in the database");
        }
        SimpleMailMessage message = createConfirmationEmail(optionalPayment.get());
        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error(e.getMessage());
        }
    }


    @Bean
    public void initializeMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("skylink");
        mailSender.setPassword("skylink123");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        this.mailSender = mailSender;
    }

    private SimpleMailMessage createConfirmationEmail(Payment payment) {
        SimpleMailMessage message = new SimpleMailMessage();
        Reservation reservation = payment.getReservation();
        Flight flight = reservation.getFlight();
        message.setSubject("Booking confirmation for " + flight.getOriginAirport().getCity() + " - " +
                flight.getDestinationAirport().getCity());
        message.setFrom("smtp.gmail.com");
        Passenger passenger = reservation.getPassenger();
        message.setTo(passenger.getEmail());
        message.setSentDate(new Date());
        String body = createConfirmationEmailBody(passenger, flight, payment);
        message.setText(body);
        return message;
    }

    private String createConfirmationEmailBody(Passenger passenger, Flight flight, Payment payment) {
        Set<Passenger> dependents = passenger.getDependents();
        StringBuilder passengerDependents = new StringBuilder();
        if (passenger.getDependents() != null) {
            for (Passenger dependent : dependents) {
                passengerDependents.append(dependent.getFirstName()).append(" ").append(dependent.getLastName());
            }
        }
        return new StringBuilder().append("Flight confirmed").append("\n\n\n").append("Hi ").append(passenger.getFirstName()).
                append("\n").append("Your ").append(flight.getOriginAirport().getCity()).append(" - ").
                append(flight.getDestinationAirport().getCity()).append(" ").append("flight is confirmed").append("\n").
                append("We will email you your ticket shortly").append("\n\n\n\n\n").append("Just Booked" + "\n").
                append(flight.getAirline().getName()).append(" ").append(flight.getName()).append("\n").
                append(flight.getOriginAirport().getCity()).append(" -> ").append(flight.getDestinationAirport().getCity())
                .append("       ").append(flight.getDeparture()).append(" - ").append(flight.getArrival()).append("\n\n\n").
                append("\n Travelers " + "\n").append(passenger.getFirstName()).append(" ").append(passenger.getLastName()).
                append("\n").append("\n").append(passengerDependents).append("\n").append("Payment receipt \n").
                append(payment.getMethod().name()).append("\n Credit Card \n").append(payment.getCreditCardNumber()).
                append("\n ").append("Total Charge \n").append(payment.getAmount()).toString();
    }

}
