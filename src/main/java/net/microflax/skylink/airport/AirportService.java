package net.microflax.skylink.airport;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService extends AbstractService<Airport> {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public void persist(Airport airport) {
        airportRepository.save(airport);
    }

}
