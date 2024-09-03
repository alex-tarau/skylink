package net.microflax.skylink.airplane;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class AirplaneService extends AbstractService<Airplane,Integer> {

    @Autowired
    private AirplaneRepository airplaneRepository;


    @Override
    protected JpaRepository<Airplane,Integer> getRepository() {
        return airplaneRepository;
    }

    @Override
    protected Airplane preSave(Airplane airplane) {
        // In the future, the airplane might have additional attributes to populate before persist the airline entity
        return null;
    }

}
