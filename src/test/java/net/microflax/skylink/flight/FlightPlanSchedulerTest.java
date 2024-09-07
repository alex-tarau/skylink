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
class FlightPlanSchedulerTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightDetailRepository flightDetailRepository;

    @InjectMocks
    private FlightPlanScheduler flightPlanScheduler;

    private Flight flight;
    private FlightDetail flightDetail;

    @BeforeEach
    void setUp() {
        flight = new Flight();
        flight.setDaysOfWeek(EnumSet.allOf(DayOfWeek.class));
        flightDetail = new FlightDetail();
        flightDetail.setFlight(flight);
        flightDetail.setFlightDate(LocalDate.now());
    }

    @Test
    void noFlightsToSchedule() {
        when(flightRepository.findAll()).thenReturn(Collections.emptyList());
        flightPlanScheduler.run();
        verify(flightDetailRepository, never()).save(any(FlightDetail.class));
    }

    @Test
    void flightsAlreadyScheduleOnTime() {
        when(flightRepository.findAll()).thenReturn(Collections.singletonList(flight));
        when(flightDetailRepository.findById(any(FlightDetail.Id.class))).thenReturn(Optional.of(flightDetail));
        flightPlanScheduler.run();
        verify(flightDetailRepository, never()).save(any(FlightDetail.class));
    }

    @Test
    void scheduleFlights() {
        when(flightRepository.findAll()).thenReturn(Collections.singletonList(flight));
        when(flightDetailRepository.findById(any(FlightDetail.Id.class))).thenReturn(Optional.empty());
        when(flightDetailRepository.save(any(FlightDetail.class))).thenReturn(flightDetail);
        flightPlanScheduler.run();
        verify(flightDetailRepository,atLeastOnce()).save(any(FlightDetail.class));
    }

}