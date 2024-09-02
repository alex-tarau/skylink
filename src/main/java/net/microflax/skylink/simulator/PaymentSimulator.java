package net.microflax.skylink.simulator;

import net.microflax.skylink.payment.Payment;
import net.microflax.skylink.payment.PaymentRepository;
import net.microflax.skylink.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentSimulator extends AbstractSimulator<Payment, Integer> {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SimulatorProperties properties;

    private static final ThreadLocal<Reservation> RESERVATION = new ThreadLocal<>();

    @Override
    protected JpaRepository<Payment, Integer> getRepository() {
        return paymentRepository;
    }

    @Override
    protected Payment retriveElement() {
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
    protected void postSave(Payment element) {
        super.postSave(element);
        RESERVATION.remove();
    }

    public void attachReservation(Reservation reservation) {
        RESERVATION.set(reservation);
    }

}
