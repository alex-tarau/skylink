package net.microflax.skylink;

import com.github.javafaker.*;
import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airport.Airport;
import net.microflax.skylink.airport.Location;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.passenger.Passport;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public abstract class AbstractSimulator {

    private final Faker faker = new Faker();
    protected static final int NUMBER_OF_ENTITIES_TO_PERSIST = 6;

    public void run(){
        int i=0;
        while (i < NUMBER_OF_ENTITIES_TO_PERSIST){
            simulate();
            i++;
        }
    }
    protected abstract void simulate();


    public final Airline createAirline() {
        Airline airline = new Airline();
        airline.setContactNumber(faker.phoneNumber().phoneNumber());
        String countryName = faker.country().name();
        airline.setName("Air " + countryName);
        airline.setOperatingRegion(countryName);
        return airline;
    }

    public final Airport createAirport() {
        Airport airport = new Airport();
        airport.setAirportCode(faker.aviation().airport().substring(0, 2));
        airport.setName(faker.country().name() + "airport");
        return airport;
    }


    public final Location createLocation() {
        Location location = new Location();
        location.setStreet(faker.address().streetAddress());
        location.setCountry(faker.address().country());
        location.setCity(faker.address().cityName());
        Airport airport = createAirport();
        location.setAirport(airport);
        return location;
    }

    public final Passenger createPassenger(Passenger passenger) {
        passenger.setFirstName(faker.name().firstName());
        passenger.setLastName(faker.name().lastName());
        passenger.setEmail(faker.internet().emailAddress());
        return passenger;
    }

    public final Passport createPassport(Passenger passenger) {
        Passport passport = new Passport();
        passport.setPassenger(passenger);
        passport.setCreatedAt(LocalDateTime.now());
        passport.setFirstName(passenger.getFirstName());
        passport.setLastName(passenger.getLastName());
        passport.setBirthDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        passport.setPassport_number(generatePassportNumber());
        passport.setExpirationDate(passport.getCreatedAt().plusYears(10));
        return passport;
    }

    public final String generatePassportNumber() {
        return faker.bothify("?" + "#".repeat(7), true);
    }


    public final Flight createFlight() {
        Flight flight = new Flight();
        flight.setArrival(faker.date().past(10, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).
                toLocalDateTime());
        flight.setDeparture(faker.date().future(1, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).
                toLocalDateTime());
        flight.setAvailableSeats((int) faker.number().randomNumber(3, true));
        flight.setOriginAirport(createAirport());
        flight.setDestinationAirport(createAirport());
        flight.setAirline(createAirline());
        flight.setFlightNumber(generateFlightNumber(flight));
        return flight;
    }

    public final String generateFlightNumber(Flight flight) {
        String[] data = flight.getAirline().getName().split(" ");
        return String.valueOf(data[0].charAt(0)) + data[1].charAt(0) + faker.number().randomNumber(4,
                false);
    }
}
