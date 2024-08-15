package net.microflax.skylink.passenger;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.dataset.annotation.Component;
import net.microfalx.bootstrap.dataset.annotation.Filterable;
import net.microfalx.bootstrap.jdbc.entity.TimestampAware;
import net.microfalx.lang.annotation.Description;
import net.microfalx.lang.annotation.Position;
import net.microfalx.lang.annotation.Visible;
import net.microfalx.lang.annotation.Width;

import java.time.LocalDate;
import java.util.Objects;

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
    @Description("The first name")
    @Position(5)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @Description("The last name")
    @Position(6)
    private String lastName;

    @Column(name = "email", nullable = false, length = 100)
    @Description("The email")
    @Position(10)
    private String email;

    @Column(name = "birth_date", nullable = false)
    @Description("The birth date")
    @Position(15)
    private LocalDate birthDate;

    @Column(name = "passport_number", nullable = false, length = 9)
    @Description("The unique, identifying number that is on a passport")
    @Position(600)
    private String passport_number;

    @Column(name = "description")
    @Position(1000)
    @Component(Component.Type.TEXT_AREA)
    @Description("A description for a {name}")
    @Width("300px")
    @Filterable()
    private String description;

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
