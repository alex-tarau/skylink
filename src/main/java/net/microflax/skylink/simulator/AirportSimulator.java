package net.microflax.skylink.simulator;

import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirportSimulator extends AbstractSimulator<Airport> {

    @Autowired
    private AirportRepository airportRepository;



    @Override
    protected Airport simulate() {
        Airport airport = new Airport();
        airport.setAirportCode(getFaker().aviation().airport().substring(0, 3));
        airport.setName(getFaker().aviation().airportName());
        airport.setStreet(getFaker().address().streetName());
        airport.setCity(getFaker().address().cityName());
        airport.setCountry(getFaker().country().name());
        return airport;
    }

    @Override
    protected void save(Airport airport) {
        airportRepository.save(airport);
    }
}
