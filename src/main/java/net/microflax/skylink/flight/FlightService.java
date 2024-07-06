package net.microflax.skylink.flight;

import com.github.javafaker.Number;
import net.microflax.skylink.airline.AirlineService;
import net.microflax.skylink.airport.AirportService;
import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

@Service
public class FlightService extends AbstractService<Flight> {


    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportService airportService;

    private final Number number = getFaker().number();

    @Autowired
    private AirlineService airlineService;


    @Override
    public void persist(Flight flight) {
        flight.setFlightNumber(generateFlightNumber(flight));
        flightRepository.save(flight);
    }

    @Override
    public void generate() {
        int numberOfFlights = 0;
        while (numberOfFlights < NUMBER_OF_ENTITIES_TO_PERSIST) {
            flightRepository.save(createFlight());
            numberOfFlights++;
        }
    }

    private Flight createFlight() {
        Flight flight = new Flight();
        flight.setArrival(getDateAndTime().past(10, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).
                toLocalDateTime());
        flight.setDeparture(getDateAndTime().future(1, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).
                toLocalDateTime());
        flight.setAvailableSeats((int) number.randomNumber(3, true));
        flight.setOriginAirport(airportService.createAirport());
        flight.setDestinationAirport(airportService.createAirport());
        //flight.setAirline(airlineService.createAirline());
        flight.setFlightNumber(generateFlightNumber(flight));
        return flight;
    }

    private String generateFlightNumber(Flight flight) {
        String[] data = flight.getAirline().getName().split(" ");
        return String.valueOf(data[0].charAt(0)) + data[1].charAt(0) + number.randomNumber(4,false);
    }



}
