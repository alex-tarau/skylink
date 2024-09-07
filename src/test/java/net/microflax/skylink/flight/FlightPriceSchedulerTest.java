package net.microflax.skylink.flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightPriceSchedulerTest {

    private FlightDetail flightDetail;

    @Mock
    private FlightDetailRepository flightDetailRepository;

    @InjectMocks
    private FlightPriceScheduler flightPriceScheduler;


    @BeforeEach
    void setUp(){
        flightDetail= new FlightDetail();
        flightDetail.setPrice(BigDecimal.ONE);
        flightDetail.setFlightDate(LocalDate.now().plusMonths(4));
    }

    @Test
    void noFlightsToSchedule() {
        when(flightDetailRepository.findAll()).thenReturn(Collections.emptyList());
        flightPriceScheduler.run();
        verify(flightDetailRepository, never()).save(any(FlightDetail.class));
    }

    @Test
    void setPrice(){
        when(flightDetailRepository.findAll()).thenReturn(Collections.singletonList(flightDetail));
        when(flightDetailRepository.save(any(FlightDetail.class))).thenReturn(flightDetail);
        flightPriceScheduler.run();
        verify(flightDetailRepository).save(any(FlightDetail.class));
    }

}