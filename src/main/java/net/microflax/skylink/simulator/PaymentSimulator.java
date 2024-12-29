package net.microflax.skylink.simulator;

import net.microflax.skylink.payment.Payment;
import net.microflax.skylink.payment.PaymentRepository;
import net.microflax.skylink.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class PaymentSimulator extends AbstractSimulator<Payment, Integer> {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SimulatorProperties properties;

    private static final ThreadLocal<Reservation> RESERVATION = new ThreadLocal<>();
    private static final String[] CREDIT_CARD_NUMBERS = {"378282246310005", "5610591081018250", "4012888888881881",
            "6011111111111117", "5555555555554444"};

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
        if (paymentRepository.count() == CREDIT_CARD_NUMBERS.length) return null;
        Reservation reservation = RESERVATION.get();
        Payment payment = new Payment();
        payment.setName(faker.idNumber().singaporeanFin());
        payment.setAmount(BigDecimal.valueOf(faker.number().randomDouble(2, 300, 500)));
        payment.setMethod(faker.options().option(Payment.Method.class));
        payment.setReservation(reservation);
        payment.setSentAt(LocalDateTime.now());
        payment.setStatus(faker.options().option(Payment.Status.class));
        payment.setCreditCardNumber(CREDIT_CARD_NUMBERS[random.nextInt(CREDIT_CARD_NUMBERS.length)]);
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
