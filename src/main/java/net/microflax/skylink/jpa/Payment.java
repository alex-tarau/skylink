package net.microflax.skylink.jpa;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column(name = "method", nullable = false, length = 50)
    private String method;

    @Column(name = "amount", nullable = false,precision = 2)
    private float amount;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    enum Status {
        /**
         * The payment process has started but is not complete
         */
        OPEN,
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
        /**
         * The payment is canceled
         */
        CANCEL
    }
}
