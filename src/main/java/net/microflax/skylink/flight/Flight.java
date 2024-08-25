package net.microflax.skylink.flight;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.jdbc.entity.NamedAndTimestampedIdentityAware;
import net.microfalx.lang.annotation.Description;
import net.microfalx.lang.annotation.Label;
import net.microfalx.lang.annotation.Position;
import net.microfalx.lang.annotation.Visible;
import net.microflax.skylink.DayOfWeekConverter;
import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.review.Review;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_flight")
public class Flight extends NamedAndTimestampedIdentityAware<Integer> {

    @ManyToOne
    @JoinColumn(name = "airplane_id", nullable = false)
    @Description("The airplane")
    @Position(6)
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id", nullable = false)
    @Label(value = "Origin", group = "Airports")
    @Description("The airport that the flight is leaving from")
    @Position(7)
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    @Label(value = "Destination", group = "Airports")
    @Description("The airport that the flight is arriving to")
    @Position(8)
    private Airport destinationAirport;

    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    @Description("The airline")
    @Position(9)
    private Airline airline;

    @Column(name = "days_of_week", nullable = false)
    @Position(20)
    @Description("The day of the week the flight is schedule")
    @Convert(converter = DayOfWeekConverter.class)
    private Set<DayOfWeek> daysOfWeek;

    @Column(name = "arrival_at", nullable = false)
    @Description("The time that the flight will arrive")
    @Position(600)
    private LocalTime arrival;

    @Column(name = "departure_at", nullable = false)
    @Description("The time that the flight will depart")
    @Position(601)
    private LocalTime departure;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id")
    @Visible(value = false)
    private List<Review> flightReviews;

    public void addFlightReview(Review review) {
        if (flightReviews == null) flightReviews = new ArrayList<>();
        flightReviews.add(review);
    }
}
