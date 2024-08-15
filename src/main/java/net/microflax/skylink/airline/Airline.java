package net.microflax.skylink.airline;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.microfalx.bootstrap.jdbc.entity.NamedAndTimestampedIdentityAware;
import net.microfalx.lang.annotation.Description;
import net.microfalx.lang.annotation.Position;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "skylink_airline")
public class Airline extends NamedAndTimestampedIdentityAware<Integer> {

    @Column(name = "contact_number", nullable = false, length = 20)
    @Description("The airline's phone number")
    @Position(10)
    private String contactNumber;

    @Column(name = "operating_region", nullable = false, length = 100)
    @Description("The geological region where the airline operates its flights")
    @Position(15)
    private String operatingRegion;
}
