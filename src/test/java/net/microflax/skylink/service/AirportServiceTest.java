package net.microflax.skylink.service;

import com.github.javafaker.*;
import net.microflax.skylink.jpa.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {


    @Mock
    private Faker faker;

    @Mock
    private Aviation aviation;

    @Mock
    private Address address;

    private Airport airport;
    private Location location;

    @Mock
    private AirportRepository airportRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private AirportService airportService;

    @BeforeEach
    void setUp(){
        airport= new Airport();
        location= new Location();
    }


    @Test
    void generate() {
        airportService.generate();
        verify(airportRepository,times(6)).save(airport);
        verify(locationRepository,times(6)).save(location);
    }
}