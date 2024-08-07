package net.microflax.skylink.reservation.system;

import net.microfalx.bootstrap.dataset.annotation.DataSet;
import net.microfalx.bootstrap.web.dataset.DataSetController;
import net.microflax.skylink.reservation.Reservation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("SystemReservationController")
@RequestMapping("/system/reservation")
@DataSet(model = Reservation.class, timeFilter = false)
public class ReservationController extends DataSetController<Reservation, Integer> {
}
