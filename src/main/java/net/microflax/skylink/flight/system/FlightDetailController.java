package net.microflax.skylink.flight.system;

import net.microfalx.bootstrap.dataset.annotation.DataSet;
import net.microfalx.bootstrap.web.dataset.DataSetController;
import net.microflax.skylink.flight.FlightDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("SystemFlightDetailController")
@RequestMapping("/system/flight_detail")
@DataSet(model = FlightDetail.class, timeFilter = false)
public class FlightDetailController extends DataSetController<FlightDetail, Integer> {
}
