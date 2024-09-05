package net.microflax.skylink.service;

import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airplane.AirplaneRepository;
import net.microflax.skylink.airplane.AirplaneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AirplaneServiceTest {
    @Mock
    private AirplaneRepository airplaneRepository;

    private Airplane airplane;

    @InjectMocks
    private AirplaneService airplaneService;

    @BeforeEach
    void setUp(){
        airplane= new Airplane();
    }

}