package net.microflax.skylink.airport;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.jdbc.entity.NamedAndTimestampedIdentityAware;
import net.microfalx.lang.annotation.Description;
import net.microfalx.lang.annotation.Position;
import net.microflax.skylink.review.Review;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_airport")
public class Airport extends NamedAndTimestampedIdentityAware<Integer> {

    @Column(name = "code", nullable = false, length = 3)
    @Description("The IATA airport code use to identify the airport")
    @Position(2)
    private String airportCode;

    @Column(name = "street", nullable = false, length = 100)
    @Description("The street that the airport is located on")
    @Position(6)
    private String street;

    @Column(name = "city", nullable = false, length = 100)
    @Description("The city that the airport is located on")
    @Position(7)
    private String city;

    @Column(name = "country", nullable = false, length = 100)
    @Description("The country that the airport is located on")
    @Position(8)
    private String country;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "airport_id")
    @Position(10)
    @Description("All of the reviews for the airport")
    private List<Review> airportReviews;

    public void addAirportReview(Review review) {
        if (airportReviews == null) airportReviews = new ArrayList<>();
        airportReviews.add(review);
    }
}
