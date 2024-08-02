package net.microflax.skylink.flight;

import net.microflax.skylink.AbstractService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class FlightService extends AbstractService {


    @Autowired
    private FlightRepository flightRepository;

    /**
     * Persist the flight in the database
     *
     * @param flight the flight
     */
    public void persistFlight(Flight flight) {
        flight.setCreatedAt(LocalDateTime.now());
        flightRepository.save(flight);
    }

}
