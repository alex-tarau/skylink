package net.microflax.skylink.payment.system;

import net.microfalx.bootstrap.dataset.annotation.DataSet;
import net.microfalx.bootstrap.web.dataset.DataSetController;
import net.microflax.skylink.payment.Payment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("SystemPaymentController")
@RequestMapping("/system/payment")
@DataSet(model = Payment.class, timeFilter = false)
public class PaymentController extends DataSetController<Payment, Integer> {
}
