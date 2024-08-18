package net.microflax.skylink.simulator;

import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airplane.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class AirplaneSimulator extends AbstractSimulator<Airplane> {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    protected Airplane simulate() {
        Airplane airplane= new Airplane();
        airplane.setName(getFaker().aviation().airplane());
        airplane.setDeliveryDate(LocalDate.now());
        airplane.setSerialNumber(getFaker().idNumber().valid());
        airplane.setManufacturer(getFaker().company().name());
        airplane.setModel(getFaker().aviation().airplane());
        airplane.setModelYear((int) getFaker().time().past(20, ChronoUnit.YEARS));
        airplane.setEconomySeats(getFaker().number().numberBetween(200, 851));
        airplane.setEconomyPlusSeats(getFaker().number().numberBetween(200, 851));
        airplane.setBusinessSeats(getFaker().number().numberBetween(200, 851));
        airplane.setFirstClassSeats(getFaker().number().numberBetween(200, 851));
        return airplane;
    }

    @Override
    protected void save(Airplane airplane) {
        airplaneRepository.save(airplane);
    }
}
