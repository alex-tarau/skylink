package net.microflax.skylink.service;

import net.microflax.skylink.airport.*;
import net.microflax.skylink.simulator.AirportSimulator;
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

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    @BeforeEach
    void setUp() {
        airport = new Airport();
        when(airportRepository.save(airport)).thenReturn(airport);
    }


    @Test
    void persist() {
        airportService.persist(airport);
        verify(airportRepository).save(airport);
    }
}