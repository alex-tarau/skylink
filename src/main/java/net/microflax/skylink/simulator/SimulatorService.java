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

    @Override
    public void afterPropertiesSet() {
        initializeAirports();
        initializeAirlines();
        initializePassengers();
        initializeFlights();
    }

    private void initializeAirports() {
        SimulationTask<Airport> airportSimulationTask = new SimulationTask<>(airportSimulator);
        int i = 0;
        while (i < simulatorProperties.getNumberOfAirports()) {
            airportSimulationTask.run();
            i++;
        }
    }

    private void initializeAirlines() {
        SimulationTask<Airline> airlineSimulationTask = new SimulationTask<>(airlineSimulator);
        int i = 0;
        while (i < simulatorProperties.getNumberOfEntities()) {
            airlineSimulationTask.run();
            i++;
        }
    }

    private void initializePassengers() {
        SimulationTask<Passenger> passengerSimulationTask = new SimulationTask<>(passengerSimulator);
        int i = 0;
        while (i < simulatorProperties.getNumberOfEntities()) {
            passengerSimulationTask.run();
            i++;
        }
    }

    private void initializeFlights() {
        SimulationTask<Flight> flightSimulationTask = new SimulationTask<>(flightSimulator);
        int i = 0;
        while (i < simulatorProperties.getNumberOfEntities()) {
            flightSimulationTask.run();
            i++;
        }
    }
}
