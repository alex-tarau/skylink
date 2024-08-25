package net.microflax.skylink.flight;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.jdbc.entity.NamedAndTimestampedIdentityAware;
import net.microfalx.lang.annotation.Description;
import net.microfalx.lang.annotation.Position;

import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_flight_status")
public class FlightStatus extends NamedAndTimestampedIdentityAware<Integer> {

    @ManyToOne
    @JoinColumn(name = "flight_id",nullable = false)
    @Description("The flight the passenger will make a reservation")
    @Position(10)
    private Flight flight;

    @Column(name = "flight_date",nullable = false)
    @Description("The date the flight is schedule")
    @Position(15)
    private LocalDate flightDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Position(20)
    @Description("The flight status")
    private Status status;

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
}
