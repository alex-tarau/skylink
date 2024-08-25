package net.microflax.skylink.simulator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
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
    private AirplaneSimulator airplaneSimulator;

    @Autowired
    private PaymentSimulator paymentSimulator;

    @Autowired
    private PassengerSimulator passengerSimulator;

    @Autowired
    private ReservationSimulator reservationSimulator;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public void afterPropertiesSet() {
        initializeSchedulers();
    }

    private void initializeSchedulers() {
        if (!simulatorProperties.isEnabled()) return;
        taskExecutor.execute(new SimulationTask<>(airlineSimulator));
        taskExecutor.execute(new SimulationTask<>(airportSimulator));
        taskExecutor.execute(new SimulationTask<>(airplaneSimulator));
        taskExecutor.execute(new SimulationTask<>(flightSimulator));

        PeriodicTrigger trigger = new PeriodicTrigger(simulatorProperties.getInterval());
        taskScheduler.schedule(new SimulationTask<>(passengerSimulator), trigger);
        taskScheduler.schedule(new SimulationTask<>(reservationSimulator), trigger);
    }
}
