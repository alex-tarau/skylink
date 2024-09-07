package net.microflax.skylink.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
class FlightPlanScheduler implements Runnable {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightDetailRepository flightDetailRepository;

    @Autowired
    private FlightService flightService;

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
        FlightDetail.Id id = new FlightDetail.Id(flight, date);
        Optional<FlightDetail> optionalFlightStatus = flightDetailRepository.findById(id);
        if (optionalFlightStatus.isPresent()) return;
        FlightDetail flightDetail = new FlightDetail();
        flightDetail.setId(id);
        flightDetail.setStatus(FlightDetail.Status.ON_SCHEDULE);
        flightService.initializePrice(flightDetail);
        flightDetailRepository.save(flightDetail);
    }
}
