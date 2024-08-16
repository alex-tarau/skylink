package net.microflax.skylink.airplane;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

public class AirplaneService extends AbstractService<Airplane> {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public void persist(Airplane airplane) {
        airplaneRepository.save(airplane);
    }
}
