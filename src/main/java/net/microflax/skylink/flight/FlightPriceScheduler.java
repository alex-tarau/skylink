package net.microflax.skylink.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class FlightPriceScheduler implements Runnable {

    @Autowired
    private FlightDetailRepository flightDetailRepository;

    FlightService flightService;

    @Override
    public void run() {
        List<FlightDetail> flightDetails = flightDetailRepository.findAll();
        flightDetails.forEach(flightDetail -> {
            flightDetail.setPrice(flightService.calculatePrices(flightDetail));
            flightDetailRepository.save(flightDetail);
        });
    }


}
