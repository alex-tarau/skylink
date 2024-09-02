package net.microflax.skylink.simulator;

import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.flight.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

@Component
public class FlightSimulator extends AbstractCacheSimulator<Flight, Integer> {

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

    @Override
    protected JpaRepository<Flight, Integer> getRepository() {
        return flightRepository;
    }

    @Override
    protected boolean shouldCreate() {
        return getElementCount() < properties.getMaximumFlightCount();
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
        float value = random.nextFloat();
        if (value < 0.3) {
            flight.setDaysOfWeek(EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY));
        } else if (value < 0.6) {
            flight.setDaysOfWeek(EnumSet.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SATURDAY));
        } else {
            flight.setDaysOfWeek(EnumSet.allOf(DayOfWeek.class));
        }
        flight.setArrival(faker.timeAndDate().past(3, TimeUnit.HOURS).atZone(ZoneId.systemDefault()).
                toLocalTime());
        flight.setDeparture(faker.timeAndDate().future(2, TimeUnit.HOURS).atZone(ZoneId.systemDefault()).
                toLocalTime());
        flight.setOriginAirport(airportSimulator.next());
        flight.setDestinationAirport(airportSimulator.next());
        return flight;
    }

}
