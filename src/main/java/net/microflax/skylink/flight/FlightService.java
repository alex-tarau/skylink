package net.microflax.skylink.flight;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class FlightService extends AbstractService<Flight,Integer> implements InitializingBean {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private FlightScheduler flightScheduler;

    @Override
    public void afterPropertiesSet() {
        taskScheduler.scheduleAtFixedRate(flightScheduler, Duration.ofMinutes(60));
    }


    @Override
    protected JpaRepository<Flight, Integer> getRepository() {
        return flightRepository;
    }

    @Override
    protected Flight preSave(Flight flight) {
        // In the future, the flight might have additional attributes to populate before persist the airline entity
        return null;
    }
}
