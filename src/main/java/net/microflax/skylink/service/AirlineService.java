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
public class AirlineService extends AbstractService<Airline> {

    @Autowired
    private AirlineRepository airlineRepository;
    private final PhoneNumber phoneNumber = getFaker().phoneNumber();


    @Override
    public void persist(Airline airline) {

    }

    @Override
    public void generate() {
        int numberOfAirlines=0;
        while (numberOfAirlines < NUMBER_OF_ENTITIES_TO_PERSIST){
            airlineRepository.save(createAirline());
            numberOfAirlines++;
        }
    }

    public Airline createAirline() {
        Airline airline = new Airline();
        airline.setContactNumber(phoneNumber.phoneNumber());
        String countryName = getCountry().name();
        airline.setName("Air " + countryName);
        airline.setOperatingRegion(countryName);
        return airline;
    }

}
