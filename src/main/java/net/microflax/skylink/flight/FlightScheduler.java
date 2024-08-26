package net.microflax.skylink.flight;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FlightScheduler implements Runnable {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightStatusRepository flightStatusRepository;

    private final static int DAYS_IN_ADVANCE = 30;

    @Override
    public void run() {
        List<Flight> flights = flightRepository.findAll();
        if (flights.isEmpty()) return;
        flights.forEach(flight -> {
            LocalDate current = LocalDate.now();
            LocalDate end = current.plusDays(DAYS_IN_ADVANCE + 1);
            while (current.isBefore(end)) {
                validate(flight, current);
                current = current.plusDays(1);
            }
        });
    }

    private void validate(Flight flight, LocalDate date) {
        if (!flight.getDaysOfWeek().contains(date.getDayOfWeek())) return;
        Optional<FlightStatus> optionalFlightStatus = flightStatusRepository.findByFlightAndFlightDate(
                new FlightStatus.Id(flight, date));
        if (optionalFlightStatus.isPresent()) return;
        FlightStatus flightStatus = new FlightStatus();
        flightStatus.setFlight(flight);
        flightStatus.setFlightDate(date);
        flightStatus.setStatus(FlightStatus.Status.ON_SCHEDULE);
        flightStatusRepository.save(flightStatus);
    }
}
