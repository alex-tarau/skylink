package net.microflax.skylink.airport;

import com.github.javafaker.Address;
import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService extends AbstractService {


    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AirportSimulator airportSimulator;


    /**
     * Persist the airport with its location in the database
     *
     * @param airport  the airport
     * @param location the location
     */
    public void persist(Airport airport, Location location) {
        airportRepository.save(airport);
        location.setAirport(airport);
        locationRepository.save(location);
    }

    @Override
    public void generate() {
        airportSimulator.run();
    }


}
