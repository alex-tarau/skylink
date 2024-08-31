package net.microflax.skylink.flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightSchedulerTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightStatusRepository flightStatusRepository;

    @InjectMocks
    private FlightScheduler flightScheduler;

    private Flight flight;
    private FlightStatus flightStatus;

    @BeforeEach
    void setUp() {
        flight = new Flight();
        flight.setDaysOfWeek(EnumSet.allOf(DayOfWeek.class));
        flightStatus = new FlightStatus();
        flightStatus.setFlight(flight);
        flightStatus.setFlightDate(LocalDate.now());
    }

    @Test
    void noFlightsToSchedule() {
        when(flightRepository.findAll()).thenReturn(Collections.emptyList());
        flightScheduler.run();
        verify(flightStatusRepository, never()).save(any(FlightStatus.class));
    }

    @Test
    void flightsAlreadyScheduleOnTime() {
        when(flightRepository.findAll()).thenReturn(Collections.singletonList(flight));
        when(flightStatusRepository.findById(any(FlightStatus.Id.class))).thenReturn(Optional.of(flightStatus));
        flightScheduler.run();
        verify(flightStatusRepository, never()).save(any(FlightStatus.class));
    }

    @Test
    void scheduleFlights() {
        when(flightRepository.findAll()).thenReturn(Collections.singletonList(flight));
        when(flightStatusRepository.findById(any(FlightStatus.Id.class))).thenReturn(Optional.empty());
        when(flightStatusRepository.save(any(FlightStatus.class))).thenReturn(flightStatus);
        flightScheduler.run();
        verify(flightStatusRepository,atLeastOnce()).save(any(FlightStatus.class));
    }

}