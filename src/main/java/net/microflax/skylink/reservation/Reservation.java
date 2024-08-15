package net.microflax.skylink.reservation;

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
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.passenger.Passenger;

import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_reservation")
public class Reservation extends TimestampAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Position(1)
    @Visible(false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @Description("The flight the passenger will make a reservation")
    @Position(2)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    @Description("The passenger that will make a flight reservation")
    @Position(3)
    private Passenger passenger;

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
        if (!(o instanceof Reservation that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
