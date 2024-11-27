package net.microflax.skylink.payment;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment==null) return;
        if (payment.getSentAt() == null) {
            payment.setStatus(Payment.Status.FAIL);
        }else {
            payment.setStatus(Payment.Status.SUCCESS);
        }
        paymentRepository.save(payment);
    }
}
