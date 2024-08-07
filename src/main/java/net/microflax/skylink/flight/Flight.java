package net.microflax.skylink.flight;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.jdbc.entity.TimestampAware;
import net.microfalx.lang.annotation.Position;
import net.microfalx.lang.annotation.Visible;
import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airport.Airport;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "flight")
public class Flight extends TimestampAware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Position(1)
    @Visible(false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id")
    @Position(2)
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    @Position(3)
    private Airport destinationAirport;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    @Position(4)
    private Airline airline;

    @Column(name = "flight_number", unique = true, nullable = false)
    @Position(5)
    private String flightNumber;

    @Column(name = "arrival_at", nullable = false)
    @Position(600)
    private LocalDateTime arrival;

    @Column(name = "departure_at", nullable = false)
    @Position(601)
    private LocalDateTime departure;

    @Column(name = "available_seats", nullable = false)
    @Position(700)
    private int availableSeats;

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
