package net.microflax.skylink.passenger;

import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.dataset.annotation.Component;
import net.microfalx.bootstrap.dataset.annotation.Filterable;
import net.microfalx.bootstrap.jdbc.entity.TimestampAware;
import net.microfalx.lang.annotation.*;
import net.microflax.skylink.review.Review;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_passenger")
public class Passenger extends TimestampAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Position(1)
    @Visible(false)
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    @Name
    @Description("The first name")
    @Position(5)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @Name
    @Description("The last name")
    @Position(6)
    private String lastName;

    @Column(name = "email", nullable = false, length = 100)
    @Email(message = "Not a valid email address")
    @Description("The email")
    @Position(10)
    private String email;

    @Column(name = "birth_date", nullable = false)
    @Description("The birth date")
    @Position(15)
    private LocalDate birthDate;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    @Visible(value = false)
    private Set<Passenger> dependents;

    @Column(name = "passport_number", nullable = false, length = 9)
    @Description("The unique, identifying number that is on a passport")
    @Position(600)
    private String passportNumber;

    @Column(name = "user_name", unique = true, length = 100)
    @Position(700)
    @Visible(value = false)
    private String userName;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id")
    @Visible(value = false)
    private List<Review> passengerReviews;

    @Column(name = "description")
    @Position(1000)
    @Component(Component.Type.TEXT_AREA)
    @Description("A description for a {name}")
    @Width("300px")
    @Filterable()
    private String description;

    public void addChildPassenger(Passenger childPassenger) {
        if (dependents == null) dependents = new HashSet<>();
        dependents.add(childPassenger);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger passenger)) return false;
        return id == passenger.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
