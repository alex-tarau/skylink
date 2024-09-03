package net.microflax.skylink.airline;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AirlineService extends AbstractService<Airline,Integer> {

    @Autowired
    private AirlineRepository airlineRepository;

    @Override
    protected JpaRepository<Airline, Integer> getRepository() {
        return airlineRepository;
    }

    @Override
    protected Airline preSave(Airline airline) {
        // In the future, the airline might have additional attributes to populate before persist the airline entity
        return null;
    }
}
