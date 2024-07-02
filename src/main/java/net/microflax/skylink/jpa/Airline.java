package net.microflax.skylink.jpa;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "airline")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "contact_number",nullable = false,length = 20)
    private String contactNumber;

    @Column(name = "operating_region",nullable = false,length = 100)
    private String operatingRegion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getOperatingRegion() {
        return operatingRegion;
    }

    public void setOperatingRegion(String operatingRegion) {
        this.operatingRegion = operatingRegion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Airline airline)) return false;
        return id == airline.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", operatingNumber='" + operatingRegion + '\'' +
                '}';
    }
}
