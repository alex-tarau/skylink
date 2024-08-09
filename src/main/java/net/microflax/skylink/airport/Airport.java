package net.microflax.skylink.airport;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.jdbc.entity.NamedIdentityAware;
import net.microfalx.lang.annotation.Position;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_airport")
public class Airport extends NamedIdentityAware<Integer> {

    @Column(name = "code", nullable = false, length = 3)
    @Position(2)
    private String airportCode;

    @Column(name = "street", nullable = false, length = 100)
    @Position(6)
    private String street;

    @Column(name = "city", nullable = false, length = 100)
    @Position(7)
    private String city;

    @Column(name = "country", nullable = false, length = 100)
    @Position(8)
    private String country;
}
