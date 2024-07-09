package net.microflax.skylink.service;

import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.flight.FlightRepository;
import net.microflax.skylink.passenger.Passenger;
import net.microflax.skylink.reservation.Reservation;
import net.microflax.skylink.reservation.ReservationRepository;
import net.microflax.skylink.reservation.ReservationService;
import net.microflax.skylink.reservation.ReservationSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationSimulator reservationSimulator;

    private Flight flight;
    private Passenger passenger;
    private Reservation reservation;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        flight = new Flight();
        flight.setId(1);
        flight.setAvailableSeats(200);
        passenger = new Passenger();
        reservation = new Reservation();
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);
    }

    @Test
    void persist() {
        when(reservationRepository.findById(anyInt())).thenReturn(Optional.of(reservation));
        when(flightRepository.save(flight)).thenReturn(flight);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        reservationService.persistReservation(reservation);
        verify(flightRepository).save(flight);
        verify(reservationRepository).save(reservation);
    }
}