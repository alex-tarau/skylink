package net.microflax.skylink.airline;

import com.github.javafaker.PhoneNumber;
import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService extends AbstractService<Airline> {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private AirlineSimulator airlineSimulator;

    @Override
    public void persist(Airline airline) {
        airlineRepository.save(airline);
    }

    @Override
    public void generate() {
        airlineSimulator.run();
    }



}
