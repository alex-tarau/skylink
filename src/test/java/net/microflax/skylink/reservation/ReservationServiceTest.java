package net.microflax.skylink.reservation;

import net.microflax.skylink.flight.Flight;
import net.microflax.skylink.passenger.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

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
        passenger = new Passenger();
        reservation = new Reservation();
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
    }

    @Test
    void persist() {
        reservationService.persistReservation(reservation);
        verify(reservationRepository).save(reservation);
    }
}