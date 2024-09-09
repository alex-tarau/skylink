package net.microflax.skylink.flight;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;
import net.microfalx.bootstrap.dataset.annotation.Component;
import net.microfalx.bootstrap.dataset.annotation.Filterable;
import net.microfalx.bootstrap.jdbc.entity.TimestampAware;
import net.microfalx.lang.annotation.Description;
import net.microfalx.lang.annotation.Name;
import net.microfalx.lang.annotation.Position;
import net.microfalx.lang.annotation.Width;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "skylink_flight_detail")
@Getter
@Setter
@ToString(callSuper = true)
public class FlightDetail extends TimestampAware {

    @EmbeddedId
    private Id id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false, insertable = false, updatable = false)
    @Description("The flight for the schedule")
    @Position(1)
    @Name
    private Flight flight;

    @Column(name = "flight_date", nullable = false, insertable = false, updatable = false)
    @Description("The date the flight is schedule")
    @Position(2)
    @Name
    private LocalDate flightDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Position(3)
    @Description("The flight status")
    private Status status;

    @Column(name = "price", nullable = false)
    @Digits(integer = 10, fraction = 2)
    @Description("The current price of the flight")
    @Position(4)
    private BigDecimal price;

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

    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @ToString
    public static class Id implements Serializable {

        @ManyToOne
        @JoinColumn(name = "flight_id", nullable = false)
        private Flight flight;

        @Column(name = "flight_date", nullable = false)
        private LocalDate flightDate;

    }
}
