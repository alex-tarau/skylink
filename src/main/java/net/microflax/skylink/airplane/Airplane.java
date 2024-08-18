package net.microflax.skylink.airplane;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "skylink_airplane")
public class Airplane extends NamedAndTimestampedIdentityAware<Integer> {

    @Column(name = "serial_number", unique = true, nullable = false, length = 10)
    @Position(2)
    @Description("To uniquely identify the airplane")
    private String serialNumber;

    @Column(name = "manufacturer", nullable = false, length = 50)
    @Position(6)
    @Description("The company that build the airplane")
    private String manufacturer;

    @Column(name = "model", nullable = false, length = 50)
    @Position(10)
    @Description("The model")
    private String model;

    @Column(name = "model_year", nullable = false)
    @Position(15)
    @Description("The model")
    private int modelYear;

    @Column(name = "economy_seats", nullable = false)
    @Position(20)
    @Description("The total number of available seats in the Economy section of the airplane")
    private int economySeats;

    @Column(name = "economy_plus_seats", nullable = false)
    @Position(21)
    @Description("The total number of available seats in the Economy Plus section of the airplane")
    private int economyPlusSeats;

    @Column(name = "business_seats", nullable = false)
    @Position(22)
    @Description("The total number of available seats in the Business section of the airplane")
    private int businessSeats;

    @Column(name = "first_class_seats", nullable = false)
    @Position(23)
    @Description("The total number of available seats in the First Class section of the airplane")
    private int firstClassSeats;

    @Column(name = "deliver_date", nullable = false)
    @Position(100)
    @Description("The delivery date")
    private LocalDate deliveryDate;
}
