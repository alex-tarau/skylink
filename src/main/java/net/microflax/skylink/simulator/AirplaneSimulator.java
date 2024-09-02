package net.microflax.skylink.simulator;

import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airplane.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class AirplaneSimulator extends AbstractCacheSimulator<Airplane, Integer> {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private SimulatorProperties properties;

    @Override
    protected JpaRepository<Airplane, Integer> getRepository() {
        return airplaneRepository;
    }

    @Override
    protected boolean shouldCreate() {
        return getElementCount() < properties.getMaximumAirplaneCount();
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

}
