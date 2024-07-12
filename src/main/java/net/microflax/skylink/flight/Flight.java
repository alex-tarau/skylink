package net.microflax.skylink.flight;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airport.Airport;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id")
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destinationAirport;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    @Column(name = "flight_number", unique = true, nullable = false)
    private String flightNumber;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "arrival_at", nullable = false)
    private LocalDateTime arrival;

    @Column(name = "departure_at", nullable = false)
    private LocalDateTime departure;

    @Column(name = "available_seats", nullable = false)
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

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", originAirport=" + originAirport +
                ", destinationAirport=" + destinationAirport +
                ", airline=" + airline +
                ", flightNumber='" + flightNumber + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
