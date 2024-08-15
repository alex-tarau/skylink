package net.microflax.skylink.payment;

import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.dataset.annotation.Component;
import net.microfalx.bootstrap.dataset.annotation.Filterable;
import net.microfalx.bootstrap.dataset.annotation.OrderBy;
import net.microfalx.lang.annotation.*;
import net.microflax.skylink.reservation.Reservation;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    @Description("The flight reservation the passenger will purchase")
    @Position(2)
    private Reservation reservation;

    @Column(name = "method", nullable = false)
    @Enumerated(EnumType.STRING)
    @Description("The payment transaction the passenger will use")
    @Position(3)
    private Method method;

    @Column(name = "amount", nullable = false, precision = 2)
    @Description("The total amount the passenger will pay for the flight reservation")
    @Position(4)
    private float amount;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Description("The status of the payment transaction")
    @Position(5)
    private Status status;

    @Column(name = "created_at", nullable = false, updatable = false)
    @NotNull
    @Position(500)
    @Visible(modes = {Visible.Mode.BROWSE, Visible.Mode.VIEW})
    @Description("The timestamp when the {name} was created")
    @net.microfalx.bootstrap.dataset.annotation.OrderBy(OrderBy.Direction.DESC)
    @CreatedDate
    @CreatedAt
    private LocalDateTime createdAt;

    @Column(name = "sent_at")
    @Description("The timestamp when the {name} was sent")
    @Position(101)
    private LocalDateTime sentAt;

    @Column(name = "modified_at")
    @Position(501)
    @Visible(modes = {Visible.Mode.BROWSE, Visible.Mode.VIEW})
    @Description("The timestamp when the {name} was last time modified")
    @LastModifiedDate
    @ModifiedAt
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
