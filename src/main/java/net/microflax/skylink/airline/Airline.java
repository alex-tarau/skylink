package net.microflax.skylink.airline;

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
@Table(name = "skylink_airline")
public class Airline extends NamedIdentityAware<Integer> {

    @Column(name = "contact_number", nullable = false, length = 20)
    @Position(10)
    private String contactNumber;

    @Column(name = "operating_region", nullable = false, length = 100)
    @Position(15)
    private String operatingRegion;
}
