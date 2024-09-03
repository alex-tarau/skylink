package net.microflax.skylink.airport;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AirportService extends AbstractService<Airport,Integer> {

    @Autowired
    private AirportRepository airportRepository;


    @Override
    protected JpaRepository<Airport, Integer> getRepository() {
        return airportRepository;
    }

    @Override
    protected Airport preSave(Airport airport) {
        // In the future, the airport might have additional attributes to populate before persist the airline entity
        return null;
    }
}
