package net.microflax.skylink.reservation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.jdbc.entity.NamedAndTimestampedIdentityAware;
import net.microfalx.lang.annotation.Description;
import net.microfalx.lang.annotation.Position;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.passenger.Passenger;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_reservation")
public class Reservation extends NamedAndTimestampedIdentityAware<Integer> {

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @Description("The flight the passenger will make a reservation")
    @Position(10)
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    @Description("The passenger that will make a flight reservation")
    @Position(15)
    private Passenger passenger;

    @Column(name = "seat", nullable = false)
    @Enumerated(EnumType.STRING)
    @Position(20)
    @Description("The seat type")
    private Seat seat;

    @Column(name = "seat_number", unique = true, nullable = false, length = 3)
    @Description("The unique identifier for the airplane seat that the passenger selected on the flight")
    @Position(21)
    private String seatNumber;

    public enum Seat {
        /**
         * The economy seat on the flight
         */
        ECONOMY,
        /**
         * The economy plus seat on the flight
         */
        ECONOMY_PLUS,
        /**
         * The business class seat on the flight
         */
        BUSINESS,
        /**
         * The first class seat on the flight
         */
        FIRST_CLASS
    }
}
