package net.microflax.skylink.simulator;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static java.time.Duration.ofMinutes;

@Configuration
@ConfigurationProperties("skylink.simulator")
@Getter
@Setter
class SimulatorProperties {

    private boolean enabled = false;
    private Duration interval = ofMinutes(1);
    private int maximumAirlineCount = 20;
    private int maximumAirplaneCount = 50;
    private int maximumAirportCount = 20;
    private int maximumFlightCount = 500;
    private int simulatePassengerCount = 5;
    private int simulateReservationCount = 2;
}
