package net.microflax.skylink.flight;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;

@Service
public class FlightService extends AbstractService implements InitializingBean {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private FlightPlanScheduler flightPlanScheduler;

    @Autowired
    private FlightPriceScheduler flightPriceScheduler;

    @Override
    public void afterPropertiesSet() {
        taskScheduler.scheduleAtFixedRate(flightPriceScheduler, Duration.ofHours(1));
        taskScheduler.scheduleAtFixedRate(flightPlanScheduler, Duration.ofHours(1));
    }

    public void initializePrice(FlightDetail flightDetail){
        flightDetail.setPrice(BigDecimal.ONE);
    }
}
