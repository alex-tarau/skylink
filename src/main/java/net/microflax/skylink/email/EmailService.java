package net.microflax.skylink.email;

import lombok.extern.slf4j.Slf4j;
import net.microfalx.lang.ExceptionUtils;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.payment.Payment;
import net.microflax.skylink.payment.PaymentRepository;
import net.microflax.skylink.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Send a confirmation email for the passenger's flight reservation
     *
     * @param id the id of the passenger's payment for the flight
     */
    @Transactional
    public void sendConfirmationEmail(int id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment == null) return;
        if (payment.getStatus() != Payment.Status.SUCCESS) return;
        SimpleMailMessage message = createConfirmationEmail(payment);
        try {
            mailSender.send(message);
        } catch (MailException e) {
            log.error("Failed to send email:{}", ExceptionUtils.getRootCauseDescription(e));
        }
    }


    public void initializeMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailProperties.getHost());
        mailSender.setPort(emailProperties.getPort());
        mailSender.setUsername(emailProperties.getUserName());
        mailSender.setPassword(emailProperties.getPassword());
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", emailProperties.isAuth());
        props.put("mail.smtp.starttls.enable", emailProperties.isTls());
        props.put("mail.debug", "true");
        this.mailSender = mailSender;
    }

    private SimpleMailMessage createConfirmationEmail(Payment payment) {
        SimpleMailMessage message = new SimpleMailMessage();
        Reservation reservation = payment.getReservation();
        Flight flight = reservation.getFlight();
        message.setSubject('✈' + "Your Flight Booking Confirmation");
        message.setFrom(emailProperties.getHost());
        Passenger passenger = reservation.getPassenger();
        message.setTo(passenger.getEmail());
        message.setSentDate(new Date());
        String body = createConfirmationEmailBody(passenger, flight, payment);
        message.setText(body);
        return message;
    }

    private String createConfirmationEmailBody(Passenger passenger, Flight flight, Payment payment) {
        Set<Passenger> dependents = passenger.getDependents();
        StringBuilder travelers = new StringBuilder();
        if (passenger.getDependents() != null) dependents.forEach(p -> travelers.append(p.getFirstName()).append(" ").append(p.getLastName()));
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Dear ").append(passenger.getFirstName()).append(" ").append(passenger.getLastName())
                .append(travelers).append(travelers).append(",").append("\n\n")
                .append("Thank you for choosing ").append(flight.getAirline().getName())
                .append(". Your flight reservation is confirmed! Below are the details of your booking")
                .append("\n\n").append("Date of Booking ")
                .append(LocalDate.now()).append("\n\n").append("Traveler(s) Details ")
                .append(passenger.getFirstName()).append(" ").append(passenger.getLastName())
                .append(", ").append(travelers).append(travelers).append("\n\n")
                .append("""
                        Flight Itinerary
                        Departure Flight
                        
                        Flight Number:\s""")
                .append(flight.getName()).append("\n").append("From: ").append(flight.getOriginAirport().getName())
                .append("\n").append("To: ").append(flight.getDestinationAirport().getName()).append("\n")
                .append("Date: [Departure Date]\n").append("Departure Time: ").append(flight.getDeparture())
                .append("\n").append("Arrival Time: ").append(flight.getArrival()).append("\n").append("""
                        Important Notes
                        Please arrive at the airport at least 3 hours before departure.
                        Ensure all passengers have valid identification or travel documents.
                        For any changes or assistance, contact us at
                        """).append(flight.getAirline().getContactNumber()).append(".\n")
                .append("We look forward to welcoming you onboard!\n").append("\n").append("Safe travels,\n")
                .append(flight.getAirline().getName()).append(" Team");
        return emailBody.toString();
    }

}
