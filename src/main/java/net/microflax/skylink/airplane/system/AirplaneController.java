package net.microflax.skylink.airplane.system;

import net.microfalx.bootstrap.dataset.annotation.DataSet;
import net.microfalx.bootstrap.web.dataset.DataSetController;
import net.microflax.skylink.airplane.Airplane;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("SystemAirplaneController")
@RequestMapping("/system/airplane")
@DataSet(model = Airplane.class, timeFilter = false)
public class AirplaneController extends DataSetController<Airplane, Integer> {
}
