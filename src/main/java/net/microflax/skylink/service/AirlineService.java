package net.microflax.skylink.service;

import com.github.javafaker.Aviation;
import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;
import net.microflax.skylink.jpa.Airline;
import net.microflax.skylink.jpa.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;
    private final Faker faker = new Faker();
    private final PhoneNumber phoneNumber = faker.phoneNumber();
    private final Country country = faker.country();


    public void persistAirlines() {
        int numberOfAirlines=0;
        while (numberOfAirlines < 6){
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
