package net.microflax.skylink.payment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.dataset.annotation.Component;
import net.microfalx.bootstrap.dataset.annotation.Filterable;
import net.microfalx.lang.annotation.Description;
import net.microfalx.lang.annotation.Position;
import net.microfalx.lang.annotation.Visible;
import net.microfalx.lang.annotation.Width;
import net.microflax.skylink.reservation.Reservation;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Position(1)
    @Visible(false)
    private int id;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    @Position(2)
    private Reservation reservation;

    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    @Position(3)
    private Method method;

    @Column(name = "amount", nullable = false, precision = 2)
    @Position(4)
    private float amount;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Position(5)
    private Status status;

    @Column(name = "created_at", nullable = false)
    @Position(100)
    private LocalDateTime createdAt;

    @Column(name = "sent_at")
    @Position(101)
    private LocalDateTime sentAt;

    @Column(name = "modified_at")
    @Position(102)
    private LocalDateTime modifiedAt;

    @Column(name = "description")
    @Position(1000)
    @Component(Component.Type.TEXT_AREA)
    @Description("A description for a {name}")
    @Width("300px")
    @Filterable()
    private String description;

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
