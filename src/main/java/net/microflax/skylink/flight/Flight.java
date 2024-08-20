package net.microflax.skylink.flight;

import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.jdbc.entity.TimestampAware;
import net.microfalx.lang.annotation.*;
import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.review.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_flight")
public class Flight extends TimestampAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Position(1)
    @Visible(false)
    private int id;

    @Column(name = "name", unique = true, nullable = false, length = 20)
    @NotBlank
    @Name
    @Position(2)
    @Description("The flight number for a {name}, which is to uniquely identify the flight")
    @Width("200px")
    private String name;

    @ManyToOne
    @JoinColumn(name = "airplane_id", nullable = false)
    @Description("The airplane")
    @Position(3)
    private Airplane airplane;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id", nullable = false)
    @Description("The airport that the flight is leaving from")
    @Position(4)
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    @Description("The airport that the flight is arriving to")
    @Position(5)
    private Airport destinationAirport;

    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    @Description("The airline")
    @Position(6)
    private Airline airline;

    @Column(name = "arrival_at", nullable = false)
    @Description("The time that the flight will arrive")
    @Position(600)
    private LocalDateTime arrival;

    @Column(name = "departure_at", nullable = false)
    @Description("The time that the flight will depart")
    @Position(601)
    private LocalDateTime departure;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id")
    @Position(900)
    @Description("All of the reviews for the flight")
    private List<Review> flightReviews;

    public void addFlightReview(Review review) {
        if (flightReviews == null) flightReviews = new ArrayList<>();
        flightReviews.add(review);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight flight)) return false;
        return id == flight.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
