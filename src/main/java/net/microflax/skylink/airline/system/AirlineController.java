package net.microflax.skylink.airline.system;

import net.microfalx.bootstrap.dataset.annotation.DataSet;
import net.microfalx.bootstrap.web.dataset.DataSetController;
import net.microflax.skylink.airline.Airline;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("SystemAirlineController")
@RequestMapping("/system/airline")
@DataSet(model = Airline.class, timeFilter = false)
public class AirlineController extends DataSetController<Airline, Integer> {
}
