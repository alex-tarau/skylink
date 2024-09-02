package net.microflax.skylink.simulator;

import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airline.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class AirlineSimulator extends AbstractCacheSimulator<Airline, Integer> {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private SimulatorProperties properties;

    @Override
    protected JpaRepository<Airline, Integer> getRepository() {
        return airlineRepository;
    }

    @Override
    protected boolean shouldCreate() {
        return getElementCount() < properties.getMaximumAirlineCount();
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

}
