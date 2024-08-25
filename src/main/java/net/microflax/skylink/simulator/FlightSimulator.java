package net.microflax.skylink.simulator;

import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.flight.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class FlightSimulator extends AbstractSimulator<Flight> {

    @Autowired
    private AirplaneSimulator airplaneSimulator;

    @Autowired
    private AirlineSimulator airlineSimulator;

    @Autowired
    private AirportSimulator airportSimulator;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SimulatorProperties properties;

    private volatile List<Flight> cache = Collections.emptyList();

    @Override
    protected int getElementCount() {
        if (cache.isEmpty()) cache = flightRepository.findAll();
        return cache.size();
    }

    @Override
    protected boolean shouldSimulate() {
        return getElementCount() < properties.getMaximumFlightCount();
    }

    @Override
    protected Flight getNextCached() {
        return cache.get(random.nextInt(cache.size()));
    }

    @Override
    protected int getSimulationCountPerIteration() {
        return properties.getMaximumFlightCount() / 10;
    }

    @Override
    protected Flight simulate() {
        Flight flight = new Flight();
        flight.setName(faker.aviation().flight());
        flight.setAirline(airlineSimulator.next());
        flight.setAirplane(airplaneSimulator.next());
        flight.setDaysOfWeek(EnumSet.allOf(DayOfWeek.class));
        flight.setArrival(faker.timeAndDate().past(10, TimeUnit.DAYS).atZone(ZoneId.systemDefault()).
                toLocalTime());
        flight.setDeparture(faker.timeAndDate().future(1, TimeUnit.DAYS).atZone(ZoneId.systemDefault()).
                toLocalTime());
        flight.setOriginAirport(airportSimulator.next());
        flight.setDestinationAirport(airportSimulator.next());
        return flight;
    }

    @Override
    protected void save(Flight flight) {
        flightRepository.save(flight);
        cache.add(flight);
    }

}
