package net.microflax.skylink.simulator;

import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class AirportSimulator extends AbstractSimulator<Airport> {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private SimulatorProperties properties;

    private volatile List<Airport> cache = Collections.emptyList();

    @Override
    protected int getElementCount() {
        if (cache.isEmpty()) cache = airportRepository.findAll();
        return cache.size();
    }

    @Override
    protected boolean shouldSimulate() {
        return getElementCount() < properties.getMaximumAirportCount();
    }

    @Override
    protected Airport getNextCached() {
        return cache.get(random.nextInt(cache.size()));
    }

    @Override
    protected int getSimulationCountPerIteration() {
        return properties.getMaximumAirportCount() / 2;
    }

    @Override
    protected Airport simulate() {
        Airport airport = new Airport();
        airport.setCode(faker.aviation().airport().substring(0, 3));
        airport.setName(faker.aviation().airportName());
        airport.setStreet(faker.address().streetName());
        airport.setCity(faker.address().cityName());
        airport.setCountry(faker.country().name());
        return airport;
    }

    @Override
    protected void save(Airport airport) {
        airportRepository.save(airport);
        cache.add(airport);
    }
}
