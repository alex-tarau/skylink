package net.microflax.skylink.airport.system;

import net.microfalx.bootstrap.dataset.annotation.DataSet;
import net.microfalx.bootstrap.web.dataset.DataSetController;
import net.microflax.skylink.airport.Airport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("SystemAirportController")
@RequestMapping("/system/airport")
@DataSet(model = Airport.class, timeFilter = false)
public class AirportController extends DataSetController<Airport, Integer> {
}
