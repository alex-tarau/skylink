package net.microflax.skylink.airport;

import net.microflax.skylink.AbstractService;
import net.microflax.skylink.simulator.AirportSimulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService extends AbstractService {

    @Autowired
    private AirportRepository airportRepository;

    /**
     * Persist the airport with its location in the database
     *
     * @param airport the airport
     */
    public void persist(Airport airport) {
        airportRepository.save(airport);
    }

}
