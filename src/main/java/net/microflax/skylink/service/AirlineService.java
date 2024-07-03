package net.microflax.skylink.service;

import com.github.javafaker.Aviation;
import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import net.microflax.skylink.jpa.Airline;
import net.microflax.skylink.jpa.AirlineRepository;
import net.microflax.skylink.jpa.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AirlineService extends AbstractService {

    @Autowired
    private AirlineRepository airlineRepository;
    private final PhoneNumber phoneNumber = faker.phoneNumber();


    @Override
    public void persist() {
        int numberOfAirlines=0;
        while (numberOfAirlines < NUMBER_OF_ENTITIES_TO_PERSIST){
            Airline airline = createAirline();
            airlineRepository.save(airline);
            numberOfAirlines++;
        }
    }

    private Airline createAirline() {
        Airline airline = new Airline();
        airline.setContactNumber(phoneNumber.phoneNumber());
        String countryName = country.name();
        airline.setName("Air " + countryName);
        airline.setOperatingRegion(countryName);
        return airline;
    }

}
