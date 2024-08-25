package net.microflax.skylink.simulator;

import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airline.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AirlineSimulator extends AbstractSimulator<Airline> {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private SimulatorProperties properties;

    private volatile List<Airline> cache = Collections.emptyList();

    @Override
    protected int getElementCount() {
        if (cache.isEmpty()) cache = airlineRepository.findAll();
        return cache.size();
    }

    @Override
    protected boolean shouldSimulate() {
        return getElementCount() < properties.getMaximumAirlineCount();
    }

    @Override
    protected Airline getNextCached() {
        return cache.get(random.nextInt(cache.size()));
    }

    @Override
    protected int getSimulationCountPerIteration() {
        return properties.getMaximumAirlineCount() / 2;
    }

    @Override
    protected Airline simulate() {
        Airline airline = new Airline();
        airline.setContactNumber(faker.phoneNumber().phoneNumber());
        airline.setName(faker.aviation().airline());
        airline.setOperatingRegion(faker.country().name());
        return airline;
    }

    @Override
    protected void save(Airline airline) {
        airlineRepository.save(airline);
        cache.add(airline);
    }
}
