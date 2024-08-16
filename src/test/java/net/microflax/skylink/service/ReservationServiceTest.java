package net.microflax.skylink.service;

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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    private Flight flight;
    private Passenger passenger;
    private Reservation reservation;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        flight = new Flight();
        flight.setId(1);
        passenger = new Passenger();
        reservation = new Reservation();
        reservation.setPassenger(passenger);
        reservation.setFlight(flight);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
    }

    @Test
    void persist() {
        reservationService.persist(reservation);
        verify(reservationRepository).save(reservation);
    }
}