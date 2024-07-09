package net.microflax.skylink.service;

import net.microflax.skylink.payment.Payment;
import net.microflax.skylink.payment.PaymentRepository;
import net.microflax.skylink.payment.PaymentService;
import net.microflax.skylink.payment.PaymentSimulator;
import net.microflax.skylink.reservation.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
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
        payment.setReservation(reservation);
        payment.setSentAt(LocalDateTime.now());
        when(paymentRepository.save(payment)).thenReturn(payment);
    }

    @Test
    void persist() {
        paymentService.persistPayment(payment);
        verify(paymentRepository).save(payment);
    }

    @Test
    void successfulStatus() {
        when(paymentRepository.findById(anyInt())).thenReturn(Optional.of(payment));
        paymentService.updateStatus(1);
        assertEquals(Payment.Status.SUCCESS, paymentRepository.findById(1).get().getStatus());
        verify(paymentRepository).save(payment);
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void canNotUpdateStatus() {
        when(paymentRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> paymentService.updateStatus(1));
    }
}