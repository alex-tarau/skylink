package net.microflax.skylink.simulator;

import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.reservation.Reservation;
import net.microflax.skylink.reservation.ReservationRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Component
public class ReservationSimulator extends AbstractSimulator<Reservation, Integer> {

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
    protected JpaRepository<Reservation, Integer> getRepository() {
        return reservationRepository;
    }

    @Override
    protected Reservation retriveElement() {
        return null;
    }

    @Override
    protected int getSimulationCountPerIteration() {
        return properties.getSimulateReservationCount();
    }

    @Override
    protected Reservation simulate() {
        Reservation reservation = new Reservation();
        reservation.setName(faker.idNumber().valid());
        reservation.setPassenger(passengerSimulator.next());
        reservation.setFlight(flightSimulator.next());
        reservation.setSeat(Reservation.Seat.ECONOMY);
        reservation.setSeatNumber(generateSeatNumber(reservation.getFlight(), reservation.getSeat()));
        return reservation;
    }

    @Override
    protected void postSave(Reservation reservation) {
        super.postSave(reservation);
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
