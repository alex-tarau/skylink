package net.microflax.skylink.flight;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

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

    /**
     * Calculates the price of a flight based on available const of flight, available seats, holidays, etc.
     *
     * @param flightDetail the flight details
     * @return the price
     */
    public BigDecimal calculatePrices(FlightDetail flightDetail) {
        return new FlightPriceCalculator(flightDetail, LocalDate.now()).execute();
    }

    @Override
    public void afterPropertiesSet() {
        flightPriceScheduler.flightService = this;
        flightPlanScheduler.flightService = this;
        taskScheduler.scheduleAtFixedRate(flightPriceScheduler, Duration.ofHours(1));
        taskScheduler.scheduleAtFixedRate(flightPlanScheduler, Duration.ofHours(1));
    }


}
