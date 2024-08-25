package net.microflax.skylink.simulator;

import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.reservation.Reservation;
import net.microflax.skylink.reservation.ReservationRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
public class ReservationSimulator extends AbstractSimulator<Reservation> {

    @Autowired
    private AirplaneSimulator airplaneSimulator;

    @Autowired
    private AirlineSimulator airlineSimulator;

    @Autowired
    private AirportSimulator airportSimulator;

    @Autowired
    private FlightSimulator flightSimulator;

    @Autowired
    private PassengerSimulator passengerSimulator;

    @Autowired
    private PaymentSimulator paymentSimulator;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SimulatorProperties properties;

    private final Map<Flight, Map<Reservation.Seat, Set<String>>> allocatedSeat = new ConcurrentHashMap<>();

    @Override
    protected int getElementCount() {
        return (int) reservationRepository.count();
    }

    @Override
    protected Reservation getNextCached() {
        return null;
    }

    @Override
    protected int getSimulationCountPerIteration() {
        return properties.getSimulateReservationCount();
    }

    @Override
    protected Reservation simulate() {
        Reservation reservation = new Reservation();
        reservation.setPassenger(passengerSimulator.next());
        reservation.setFlight(flightSimulator.next());
        reservation.setSeat(Reservation.Seat.ECONOMY);
        reservation.setSeatNumber(generateSeatNumber(reservation.getFlight(), reservation.getSeat()));
        return reservation;
    }

    @Override
    protected void save(Reservation reservation) {
        reservationRepository.save(reservation);
        paymentSimulator.attachReservation(reservation);
        paymentSimulator.next();
    }

    private String generateSeatNumber(Flight flight, Reservation.Seat seatType) {
        for (; ; ) {
            Map<Reservation.Seat, Set<String>> seatsPerFlight = allocatedSeat.computeIfAbsent(flight, flight1 -> new ConcurrentHashMap<>());
            Set<String> allocateSeats = seatsPerFlight.computeIfAbsent(seatType, seat -> new ConcurrentSkipListSet<>());
            String seat = RandomStringUtils.random(2, false, true).toUpperCase() +
                    RandomStringUtils.random(1, true, false).toUpperCase();
            String seatKey = flight.getName() + "_" + seat;
            if (allocateSeats.add(seatKey)) return seat;
        }
    }
}
