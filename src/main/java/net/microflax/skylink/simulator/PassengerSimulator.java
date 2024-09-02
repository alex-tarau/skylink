package net.microflax.skylink.simulator;

import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.passenger.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassengerSimulator extends AbstractSimulator<Passenger, Integer> {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private SimulatorProperties properties;

    @Override
    protected JpaRepository<Passenger, Integer> getRepository() {
        return passengerRepository;
    }

    @Override
    protected Passenger retriveElement() {
        long count = passengerRepository.count();
        Page<Passenger> page = passengerRepository.findAll(Pageable.ofSize(1).withPage(random.nextInt((int) count)));
        List<Passenger> content = page.getContent();
        return content.isEmpty() ? null : content.iterator().next();
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

}
