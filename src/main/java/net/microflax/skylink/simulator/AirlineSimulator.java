package net.microflax.skylink.simulator;

import net.datafaker.Faker;
import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airline.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirlineSimulator extends AbstractSimulator<Airline> {


    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    protected Airline simulate() {
        Airline airline = new Airline();
        airline.setContactNumber(getFaker().phoneNumber().phoneNumber());
        airline.setName(getFaker().aviation().airline());
        airline.setOperatingRegion(getFaker().country().name());
        return airline;
    }

    @Override
    protected void save(Airline airline) {
        airlineRepository.save(airline);
    }
}
