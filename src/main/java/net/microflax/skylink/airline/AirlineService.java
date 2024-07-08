package net.microflax.skylink.airline;

import com.github.javafaker.PhoneNumber;
import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService extends AbstractService {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AirlineSimulator airlineSimulator;

    /**
     * Persist the airline in the database
     * @param airline the airline
     */
    public void persistAirline(Airline airline) {
        airlineRepository.save(airline);
    }

    @Override
    public void generate() {
        airlineSimulator.run();
    }



}
