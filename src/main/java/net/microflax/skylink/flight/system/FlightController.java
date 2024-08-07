package net.microflax.skylink.flight.system;

import net.microfalx.bootstrap.dataset.annotation.DataSet;
import net.microfalx.bootstrap.web.dataset.DataSetController;
import net.microflax.skylink.flight.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("SystemFlightController")
@RequestMapping("/system/flight")
@DataSet(model = Flight.class, timeFilter = false)
public class FlightController extends DataSetController<Flight, Integer> {
}
