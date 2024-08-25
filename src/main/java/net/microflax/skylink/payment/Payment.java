package net.microflax.skylink.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.jdbc.entity.NamedAndTimestampedIdentityAware;
import net.microfalx.lang.annotation.Description;
import net.microfalx.lang.annotation.Position;
import net.microflax.skylink.reservation.Reservation;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_payment")
public class Payment extends NamedAndTimestampedIdentityAware<Integer> {

    @OneToOne
    @JoinColumn(name = "reservation_id")
    @Description("The flight reservation the passenger will purchase")
    @Position(6)
    private Reservation reservation;

    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    @Description("The payment transaction the passenger will use")
    @Position(10)
    private Method method;

    @Column(name = "credit_card_number", unique = true, nullable = false, length = 20)
    @CreditCardNumber(message = "Not a valid credit card number format")
    @Description("The passenger's credit card number")
    @Position(15)
    private String creditCardNumber;

    @Column(name = "amount", nullable = false, precision = 2)
    @Description("The total amount the passenger will pay for the flight reservation")
    @Position(20)
    private float amount;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Description("The status of the payment transaction")
    @Position(25)
    private Status status = Status.PENDING;

    @Column(name = "sent_at")
    @Description("The timestamp when the {name} was sent")
    @Position(502)
    private LocalDateTime sentAt;

    public enum Status {
        /**
         * The payment has been created in the system and is ready for processing
         */
        PENDING,
        /**
         * The payment is done
         */
        SUCCESS,
        /**
         * The payment fail
         */
        FAIL,
    }

    public enum Method {
        /**
         * payment with Visa card
         */
        VISA,
        /**
         * payment with Master card
         */
        MASTER_CARD,
        /**
         * payment with Amex card
         */
        AMEX,
        /**
         * payment with Paypal
         */
        PAYPAL,
    }
}
