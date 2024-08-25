package net.microflax.skylink.simulator;

import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airplane.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class AirplaneSimulator extends AbstractSimulator<Airplane> {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private SimulatorProperties properties;

    private volatile List<Airplane> cache = Collections.emptyList();

    @Override
    protected int getElementCount() {
        if (cache.isEmpty()) cache = airplaneRepository.findAll();
        return cache.size();
    }

    @Override
    protected boolean shouldSimulate() {
        return getElementCount() < properties.getMaximumAirplaneCount();
    }

    @Override
    protected Airplane getNextCached() {
        return cache.get(random.nextInt(cache.size()));
    }

    @Override
    protected int getSimulationCountPerIteration() {
        return properties.getMaximumAirplaneCount() / 2;
    }

    @Override
    protected Airplane simulate() {
        Airplane airplane = new Airplane();
        airplane.setName(faker.aviation().airplane());
        airplane.setDeliveryDate(LocalDate.now());
        airplane.setSerialNumber(faker.idNumber().valid().substring(0, 10));
        airplane.setName(faker.aviation().airplane());
        airplane.setManufacturer(faker.aviation().manufacturer());
        airplane.setModelYear(ThreadLocalRandom.current().nextInt(2000, 2024));
        airplane.setEconomySeats(faker.number().numberBetween(80, 100));
        airplane.setEconomyPlusSeats(faker.number().numberBetween(10, 20));
        airplane.setBusinessSeats(faker.number().numberBetween(10, 20));
        airplane.setFirstClassSeats(faker.number().numberBetween(10, 20));
        return airplane;
    }

    @Override
    protected void save(Airplane airplane) {
        airplaneRepository.save(airplane);
        cache.add(airplane);
    }
}
