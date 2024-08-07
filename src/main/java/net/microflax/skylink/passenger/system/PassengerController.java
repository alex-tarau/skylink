package net.microflax.skylink.passenger.system;

import net.microfalx.bootstrap.dataset.annotation.DataSet;
import net.microfalx.bootstrap.web.dataset.DataSetController;
import net.microflax.skylink.passenger.Passenger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("SystemPassengerController")
@RequestMapping("/system/passenger")
@DataSet(model = Passenger.class, timeFilter = false)
public class PassengerController extends DataSetController<Passenger, Integer> {
}
