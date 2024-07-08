package net.microflax.skylink.payment;

import jakarta.persistence.*;
import net.microflax.skylink.reservation.Reservation;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

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