package net.microflax.skylink.airport;

import net.microflax.skylink.AbstractSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirportSimulator extends AbstractSimulator {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    protected void simulate() {
        airportRepository.save(createAirport());
        locationRepository.save(createLocation());
    }
}
