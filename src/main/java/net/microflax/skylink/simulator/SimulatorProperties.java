package net.microflax.skylink.simulator;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Getter
@Setter
@Configuration
public class SimulatorProperties {

    private boolean enabled;
    private Duration interval = Duration.ofSeconds(20);
    private int numberOfAirports = 12;
    private int numberOfEntities = 6;
}
