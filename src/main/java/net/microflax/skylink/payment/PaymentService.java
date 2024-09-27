package net.microflax.skylink.payment;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PaymentService extends AbstractService{

    @Autowired
    private PaymentRepository paymentRepository;


    /**
     * Update the status of the payment
     *
     * @param id the id of the payment
     */
    @Transactional
    public void updateStatus(int id) {
        Optional<Payment> newPayment = paymentRepository.findById(id);
        if (newPayment.isEmpty()) throw new NoSuchElementException("There is no payment transaction in database");
        if (newPayment.get().getSentAt() == null) {
            newPayment.get().setStatus(Payment.Status.FAIL);
        }else {
            newPayment.get().setStatus(Payment.Status.SUCCESS);
        }
        paymentRepository.save(newPayment.get());
    }
}
