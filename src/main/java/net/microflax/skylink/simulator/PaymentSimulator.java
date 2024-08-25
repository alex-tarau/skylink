package net.microflax.skylink.simulator;

import net.microflax.skylink.airline.AirlineRepository;
import net.microflax.skylink.airport.AirportRepository;
import net.microflax.skylink.flight.FlightRepository;
import net.microflax.skylink.passenger.PassengerRepository;
import net.microflax.skylink.payment.Payment;
import net.microflax.skylink.payment.PaymentRepository;
import net.microflax.skylink.reservation.Reservation;
import net.microflax.skylink.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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


    @Autowired
    private SimulatorProperties properties;

    private static final ThreadLocal<Reservation> RESERVATION = new ThreadLocal<>();

    @Override
    protected int getElementCount() {
        return (int) paymentRepository.count();
    }

    @Override
    protected Payment getNextCached() {
        return null;
    }

    @Override
    protected int getSimulationCountPerIteration() {
        return 1;
    }

    @Override
    protected Payment simulate() {
        Reservation reservation = RESERVATION.get();
        Payment payment = new Payment();
        payment.setName(faker.idNumber().singaporeanFin());
        payment.setAmount((float) faker.number().randomDouble(2, 300, 500));
        payment.setMethod(Payment.Method.VISA);
        payment.setReservation(reservation);
        payment.setSentAt(LocalDateTime.now());
        payment.setCreditCardNumber(faker.business().creditCardNumber());
        return payment;
    }

    @Override
    protected void save(Payment payment) {
        paymentRepository.save(payment);
        RESERVATION.remove();
    }

    public void attachReservation(Reservation reservation) {
        RESERVATION.set(reservation);
    }

}
