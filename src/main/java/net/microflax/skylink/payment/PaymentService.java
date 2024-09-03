package net.microflax.skylink.payment;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PaymentService extends AbstractService<Payment,Integer> {

    @Autowired
    private PaymentRepository paymentRepository;


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


    @Override
    protected JpaRepository<Payment, Integer> getRepository() {
        return paymentRepository;
    }

    @Override
    protected Payment preSave(Payment payment) {
        // In the future, the payment might have additional attributes to populate before persist the airline entity
        return null;
    }
}
