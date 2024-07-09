package net.microflax.skylink.service;

import net.microflax.skylink.airport.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {


    private Airport airport;
    private Location location;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private AirportSimulator airportSimulator;

    @InjectMocks
    private AirportService airportService;

    @BeforeEach
    void setUp() {
        airport = new Airport();
        location = new Location();
        location.setAirport(airport);
        when(airportRepository.save(airport)).thenReturn(airport);
        when(locationRepository.save(location)).thenReturn(location);
    }


    @Test
    void persist() {
        airportService.persist(airport, location);
        verify(airportRepository).save(airport);
        verify(locationRepository).save(location);
    }
}