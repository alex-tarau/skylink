package net.microflax.skylink.jpa;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "airport_code", nullable = false, length = 3)
    private String airportCode;

    @Column(name = "name", unique = true, nullable = false, length = 100)
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airport airport)) return false;
        return id == airport.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", airportCode='" + airportCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
