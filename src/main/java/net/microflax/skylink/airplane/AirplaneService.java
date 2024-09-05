package net.microflax.skylink.airplane;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

public class AirplaneService extends AbstractService<Airplane,Integer> {

    @Autowired
    private AirplaneRepository airplaneRepository;

}
