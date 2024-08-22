package net.microflax.skylink.review;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.jdbc.entity.NamedAndTimestampedIdentityAware;
import net.microfalx.lang.annotation.Description;
import net.microfalx.lang.annotation.Position;
import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.passenger.Passenger;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_review")
public class Review extends NamedAndTimestampedIdentityAware<Integer> {

    @ManyToOne
    @JoinColumn(name = "passenger_id",nullable = false)
    @Position(6)
    @Description("The passenger that created the review")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    @Position(7)
    @Description("The airline that receive a review from passengers")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    @Position(8)
    @Description("The airport that receive a review from passengers")
    private Airport airport;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @Position(9)
    @Description("The flight that receive a review from passengers")
    private Flight flight;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Position(10)
    @Description("The review status")
    private Status status;

    @Column(name = "rating", nullable = false)
    @Position(100)
    @Description("The rating")
    private short rating;

    @Column(name = "helpful")
    @Position(200)
    @Description("The rating")
    private Boolean helpful;

    public enum Status {
        /**
         * The review process has not yet begun.
         */
        NOT_STARTED,
        /**
         * The review is currently underway.
         */
        IN_PROGRESS,
        /**
         * The review has been finished.
         */
        COMPLETED,
        /**
         * The review process is officially closed, with no further actions required
         */
        CLOSE
    }
}
