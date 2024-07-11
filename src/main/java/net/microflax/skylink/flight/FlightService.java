package net.microflax.skylink.flight;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        flightRepository.save(flight);
    }

}
