package net.microflax.skylink.airport;

import com.github.javafaker.Address;
import net.microflax.skylink.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
