package net.microflax.skylink.service;

import com.github.javafaker.Address;
import com.github.javafaker.Aviation;
import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import net.microflax.skylink.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AirportService extends AbstractService<Airport> {

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private LocationRepository locationRepository;
    private final Address address = getFaker().address();

    @Override
    public void persist(Airport airport) {

    }

    @Override
    public void generate() {
        int numberOfAirports = 0;
        while (numberOfAirports < NUMBER_OF_ENTITIES_TO_PERSIST) {
            Location location = createLocation();
            locationRepository.save(location);
            numberOfAirports++;
        }
    }

    public Airport createAirport() {
        Airport airport = new Airport();
        airport.setAirportCode(getAviation().airport().substring(0, 2));
        airport.setName(getCountry().name() + "airport");
        return airport;
    }


    private Location createLocation() {
        Location location = new Location();
        location.setStreet(address.streetAddress());
        location.setCountry(address.country());
        location.setCity(address.cityName());
        Airport airport = createAirport();
        airportRepository.save(airport);
        location.setAirport(airport);
        return location;
    }

}
