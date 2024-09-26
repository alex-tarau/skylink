package net.microflax.skylink.airplane;

import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneService extends AbstractService{

    @Autowired
    private AirplaneRepository airplaneRepository;

}
