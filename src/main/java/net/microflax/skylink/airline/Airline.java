package net.microflax.skylink.airline;

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
@Table(name = "skylink_airline")
public class Airline extends NamedAndTimestampedIdentityAware<Integer> {

    @Column(name = "contact_number", nullable = false, length = 20)
    @Description("The airline's phone number")
    @Position(10)
    private String contactNumber;

    @Column(name = "operating_region", nullable = false, length = 100)
    @Description("The geological region where the airline operates its flights")
    @Position(15)
    private String operatingRegion;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_id")
    @Position(20)
    @Description("All of the reviews of the airline")
    private List<Review> airlineReviews;

    public void addAirlineReview(Review review) {
        if (airlineReviews == null) airlineReviews = new ArrayList<>();
        airlineReviews.add(review);
    }
}
