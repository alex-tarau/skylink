package net.microflax.skylink.flight;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService extends AbstractService<Flight> {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void persist(Flight flight) {flightRepository.save(flight);}
}
