package net.microflax.skylink.simulator;

import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class AirportSimulator extends AbstractCacheSimulator<Airport, Integer> {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private SimulatorProperties properties;

    @Override
    protected JpaRepository<Airport, Integer> getRepository() {
        return airportRepository;
    }

    @Override
    protected boolean shouldCreate() {
        return getElementCount() < properties.getMaximumAirportCount();
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

}
