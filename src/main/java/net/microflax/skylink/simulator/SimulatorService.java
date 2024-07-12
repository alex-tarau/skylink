package net.microflax.skylink.simulator;

import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.passenger.Passenger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulatorService implements InitializingBean {

    @Autowired
    private SimulatorProperties simulatorProperties;

    @Autowired
    private FlightSimulator flightSimulator;

    @Autowired
    private AirportSimulator airportSimulator;

    @Autowired
    private AirlineSimulator airlineSimulator;

    @Autowired
    private PaymentSimulator paymentSimulator;

    @Autowired
    private PassengerSimulator passengerSimulator;

    @Autowired
    private ReservationSimulator reservationSimulator;

    @Override
    public void afterPropertiesSet() {
        simulatorProperties.setEnabled(true);
        if (simulatorProperties.isEnabled()) {
            initializeSimulator(airlineSimulator, simulatorProperties.getNumberOfEntities());
            initializeSimulator(airportSimulator, simulatorProperties.getNumberOfAirports());
            initializeSimulator(passengerSimulator, simulatorProperties.getNumberOfEntities());
            initializeSimulator(flightSimulator, simulatorProperties.getNumberOfEntities());
            initializeSimulator(reservationSimulator, simulatorProperties.getNumberOfEntities());
            initializeSimulator(paymentSimulator, simulatorProperties.getNumberOfEntities());
        }
    }

    /**
     * Initialize the simulators
     *
     * @param abstractSimulator a simulator to simulate entities
     * @param numberOfEntities  the number of entities to persist in the database
     */
    private void initializeSimulator(AbstractSimulator<?> abstractSimulator, int numberOfEntities) {
        SimulationTask<?> simulationTask = new SimulationTask<>(abstractSimulator);
        int i = 0;
        while (i < numberOfEntities) {
            simulationTask.run();
            i++;
        }
    }
}
