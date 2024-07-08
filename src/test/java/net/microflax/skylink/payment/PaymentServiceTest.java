package net.microflax.skylink.payment;

import net.microflax.skylink.reservation.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentSimulator paymentSimulator;

    private Reservation reservation;
    private Payment payment;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        reservation = new Reservation();
        payment = new Payment();
        payment.setAmount(300.67f);
        payment.setMethod(Payment.Method.CREDIT);
        when(paymentRepository.save(payment)).thenReturn(payment);
    }

    @Test
    void persist() {
        paymentService.persistPayment(payment);
        verify(paymentRepository).save(payment);
    }
}