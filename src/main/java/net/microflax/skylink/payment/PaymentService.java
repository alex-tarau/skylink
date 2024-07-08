package net.microflax.skylink.payment;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService extends AbstractService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentSimulator paymentSimulator;

    public void persistPayment(Payment payment){
        payment.setStatus(Payment.Status.SUCCESS);
        payment.setCreatedAt(LocalDateTime.now());
        paymentRepository.save(payment);
    }

    @Override
    public void generate() {
        paymentSimulator.run();
    }

    public void updateStatus(Payment payment){

    }
}
