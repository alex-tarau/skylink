package net.microflax.skylink.flight;

import net.microflax.skylink.AbstractSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightSimulator extends AbstractSimulator {


    @Autowired
    private FlightRepository flightRepository;

    @Override
    protected void simulate() {
        flightRepository.save(createFlight());
    }
}
