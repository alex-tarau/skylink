package net.microflax.skylink.airline;

import net.microflax.skylink.AbstractSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirlineSimulator extends AbstractSimulator {

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    protected void simulate() {
        airlineRepository.save(createAirline());
    }
}
