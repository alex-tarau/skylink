package net.microflax.skylink.airline;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirlineService extends AbstractService<Airline,Integer> {

    @Autowired
    private AirlineRepository airlineRepository;

}
