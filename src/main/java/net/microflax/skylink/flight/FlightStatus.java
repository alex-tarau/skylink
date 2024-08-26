package net.microflax.skylink.flight;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.dataset.annotation.Component;
import net.microfalx.bootstrap.dataset.annotation.Filterable;
import net.microfalx.bootstrap.jdbc.entity.TimestampAware;
import net.microfalx.lang.annotation.Description;
import net.microfalx.lang.annotation.Position;
import net.microfalx.lang.annotation.Width;

import java.io.Serializable;
import java.time.LocalDate;

import static net.microfalx.lang.ArgumentUtils.requireNonNull;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@IdClass(FlightStatus.Id.class)
@Table(name = "skylink_flight_status")
public class FlightStatus extends TimestampAware {

    @jakarta.persistence.Id
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    @Description("The flight the passenger will make a reservation")
    @Position(1)
    private Flight flight;

    @jakarta.persistence.Id
    @Column(name = "flight_date", nullable = false)
    @Description("The date the flight is schedule")
    @Position(2)
    private LocalDate flightDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Position(3)
    @Description("The flight status")
    private Status status;

    @Column(name = "description")
    @Position(1000)
    @Component(Component.Type.TEXT_AREA)
    @Description("A description for a {name}")
    @Width("300px")
    @Filterable()
    private String description;

    public enum Status {
        /**
         * The flight is schedule to arrive at the origin airport on time.
         */
        ON_SCHEDULE,
        /**
         * The airplane is currently flying
         */
        IN_FLIGHT,
        /**
         * The flight arrive at the destination airport
         */
        ARRIVED,
        /**
         * The flight is delayed
         */
        DELAYED,
    }

    @Getter
    @ToString
    public static class Id implements Serializable {

        private Flight flight;
        private LocalDate flightDate;

        public Id(Flight flight, LocalDate flightDate) {
            requireNonNull(flight);
            requireNonNull(flightDate);
            this.flight = flight;
            this.flightDate = flightDate;
        }
    }
}
