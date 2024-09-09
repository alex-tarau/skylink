package net.microflax.skylink.flight;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FlightPriceCalculatorTest {

    private FlightDetail flightDetail;

    @BeforeEach
    void setUp() {
        flightDetail = new FlightDetail();
        flightDetail.setFlightDate(LocalDate.of(2025, 1, 9));
    }

    @Test
    void execute() {
        FlightPriceCalculator flightPriceCalculator = new FlightPriceCalculator(flightDetail, LocalDate.of(2024,
                9, 9));
        assertNotNull(flightPriceCalculator.execute());
    }
}