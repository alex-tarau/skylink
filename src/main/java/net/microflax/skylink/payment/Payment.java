package net.microflax.skylink.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.microflax.skylink.reservation.Reservation;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    private Method method;

    @Column(name = "amount", nullable = false, precision = 2)
    private float amount;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", reservation=" + reservation +
                ", method='" + method + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", sentAt=" + sentAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

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
