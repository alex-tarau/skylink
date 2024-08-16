package net.microflax.skylink.service;

import net.microflax.skylink.airline.Airline;
import net.microflax.skylink.airline.AirlineRepository;
import net.microflax.skylink.airline.AirlineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AirlineServiceTest {


    private Airline airline;

    @Mock
    private AirlineRepository airlineRepository;


    @InjectMocks
    private AirlineService airlineService;

    @BeforeEach
    void setUp() {
        airline = new Airline();
        when(airlineRepository.save(airline)).thenReturn(airline);
    }


    @Test
    void persist() {
        airlineService.persist(airline);
        verify(airlineRepository).save(airline);
    }
}