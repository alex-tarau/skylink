package net.microflax.skylink.flight;

import com.github.javafaker.Number;
import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class FlightService extends AbstractService {


    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightSimulator flightSimulator;

    /**
     * Persist the flight in the database
     *
     * @param flight the flight
     */
    public void persistFlight(Flight flight) {
        flight.setFlightNumber(generateFlightNumber(flight));
        flight.setAvailableSeats(ThreadLocalRandom.current().nextInt(200, 851));
        flightRepository.save(flight);
    }

    @Override
    public void generate() {
        flightSimulator.run();
    }


    private String generateFlightNumber(Flight flight) {
        String[] data = flight.getAirline().getName().split(" ");
        return String.valueOf(data[0].charAt(0)) + data[1].charAt(0) + ThreadLocalRandom.current().nextInt(1_000,
                10_000);
    }


}
