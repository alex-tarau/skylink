package net.microflax.skylink.payment;

import net.microflax.skylink.AbstractSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentSimulator extends AbstractSimulator {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    protected void simulate() {
        paymentRepository.save(createPayment());
    }
}
