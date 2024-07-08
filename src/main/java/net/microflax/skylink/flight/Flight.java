package net.microflax.skylink.flight;

import jakarta.persistence.*;
import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airport.Airport;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Column(name = "departure", nullable = false)
    private LocalDateTime departure;

    @Column(name = "arrival", nullable = false)
    private LocalDateTime arrival;

    @Column(name = "available_seats", nullable = false)
    private int availableSeats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public void setArrival(LocalDateTime arrival) {
        this.arrival = arrival;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

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
