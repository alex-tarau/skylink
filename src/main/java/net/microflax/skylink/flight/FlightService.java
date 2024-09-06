package net.microflax.skylink.flight;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
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
    private FlightDetailScheduler flightDetailScheduler;

    @Override
    public void afterPropertiesSet() {
        taskScheduler.scheduleAtFixedRate(flightDetailScheduler, Duration.ofMinutes(60));
    }
}
