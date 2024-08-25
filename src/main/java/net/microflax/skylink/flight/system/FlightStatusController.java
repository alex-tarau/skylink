package net.microflax.skylink.flight.system;

import net.microfalx.bootstrap.dataset.annotation.DataSet;
import net.microfalx.bootstrap.web.dataset.DataSetController;
import net.microflax.skylink.flight.FlightStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("SystemFlightStatusController")
@RequestMapping("/system/flight_status")
@DataSet(model = FlightStatus.class, timeFilter = false)
public class FlightStatusController extends DataSetController<FlightStatus, Integer> {
}
