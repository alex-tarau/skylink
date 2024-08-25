package net.microflax.skylink.simulator;

import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.passenger.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PassengerSimulator extends AbstractSimulator<Passenger> {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private SimulatorProperties properties;

    private volatile List<Passenger> cache = Collections.emptyList();

    @Override
    protected int getElementCount() {
        if (cache.isEmpty()) cache = passengerRepository.findAll();
        return cache.size();
    }

    @Override
    protected Passenger getNextCached() {
        return cache.get(random.nextInt(cache.size()));
    }

    @Override
    protected int getSimulationCountPerIteration() {
        return properties.getSimulatePassengerCount();
    }

    @Override
    protected Passenger simulate() {
        Passenger passenger = new Passenger();
        passenger.setFirstName(faker.name().firstName());
        passenger.setLastName(faker.name().lastName());
        passenger.setEmail(passenger.getFirstName() + "." + passenger.getLastName() + "@change.me");
        passenger.setBirthDate(faker.timeAndDate().birthday());
        passenger.setPassportNumber(faker.passport().valid());
        return passenger;
    }

    @Override
    protected void save(Passenger passenger) {
        passengerRepository.save(passenger);
        cache.add(passenger);
    }
}
