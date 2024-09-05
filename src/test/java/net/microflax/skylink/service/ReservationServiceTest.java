package net.microflax.skylink.service;

import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airplane.AirplaneRepository;
import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.reservation.Reservation;
import net.microflax.skylink.reservation.ReservationRepository;
import net.microflax.skylink.reservation.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private AirplaneRepository airplaneRepository;

    @Mock
    private ReservationRepository reservationRepository;

    private Airplane airplane;
    private Flight flight;
    private Passenger passenger;
    private Reservation reservation;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        flight = new Flight();
        airplane = new Airplane();
        airplane.setEconomySeats(100);
        flight.setAirplane(airplane);
        passenger = new Passenger();
        reservation = new Reservation();
        reservation.setId(1);
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);
        reservation.setSeat(Reservation.Seat.ECONOMY);
        when(reservationRepository.findById(anyInt())).thenReturn(Optional.of(reservation));
        when(airplaneRepository.save(any(Airplane.class))).thenReturn(airplane);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
    }

    @Test
    void updateAvailableSeats() {
        reservationService.updateAvailableSeats(1, Reservation.Seat.ECONOMY);
        assertEquals(99, reservationRepository.findById(1).get().getFlight().getAirplane().getEconomySeats());
        verify(airplaneRepository).save(any(Airplane.class));
        verify(reservationRepository).save(any(Reservation.class));
    }
}