package net.microflax.skylink.payment;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PaymentService extends AbstractService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentSimulator paymentSimulator;

    public void persistPayment(Payment payment) {
        payment.setStatus(Payment.Status.PENDING);
        payment.setCreatedAt(LocalDateTime.now());
        paymentRepository.save(payment);
    }

    @Override
    public void generate() {
        paymentSimulator.run();
    }

    /**
     * Update the status of the payment
     *
     * @param id the id of the payment
     */
    public void updateStatus(int id) {
        Optional<Payment> newPayment = paymentRepository.findById(id);
        if (newPayment.isEmpty()) throw new NoSuchElementException("There is no payment transaction in database");
        if (newPayment.get().getSentAt() == null) newPayment.get().setStatus(Payment.Status.FAIL);
        newPayment.get().setStatus(Payment.Status.SUCCESS);
        paymentRepository.save(newPayment.get());
    }
}
