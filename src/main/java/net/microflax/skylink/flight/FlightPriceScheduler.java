package net.microflax.skylink.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightPriceScheduler implements Runnable {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightDetailRepository flightDetailRepository;

    @Override
    public void run() {
        List<Flight> flights = flightRepository.findAll();
        if (flights.isEmpty()) return;
        flights.forEach(flight -> {

        });
    }
}
