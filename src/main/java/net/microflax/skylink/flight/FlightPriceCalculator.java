package net.microflax.skylink.flight;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

import static net.microfalx.lang.ArgumentUtils.requireNonNull;

/**
 * A class which deals with price calculation and adjustment for flights.
 */
class FlightPriceCalculator {

    private final FlightDetail flightDetail;
    private final LocalDate date;

    FlightPriceCalculator(FlightDetail flightDetail, LocalDate date) {
        requireNonNull(flightDetail);
        requireNonNull(date);
        this.flightDetail = flightDetail;
        this.date = date;
    }

    /**
     * Gets the next price based various conditions.
     *
     * @return the price
     */
    BigDecimal execute() {
        BigDecimal base = createBase();
        return adjust(base);
    }

    private LocalDate getFlightDate() {
        return flightDetail.getFlightDate();
    }

    private BigDecimal createBase() {
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(200, 850));
    }

    private BigDecimal adjust(BigDecimal price) {
        LocalDate flightDate = flightDetail.getFlightDate();
        if (date.isBefore(flightDate.minusMonths(3))) {
            price = price.subtract(price.multiply(new BigDecimal("0.15")));
        }
        if (isSummerSeason()) {
            price = price.multiply(new BigDecimal("1.3"));
        } else if (isFallOrWinter()) {
            price = price.subtract(price.multiply(new BigDecimal("0.25")));
        } else if (isEvening()) {
            price = price.subtract(price.multiply(new BigDecimal("0.2")));
        } else if (isTuesdayOrWednesdayORSaturday()) {
            price = price.subtract(price.multiply(new BigDecimal("0.1")));
        } else if (isFridayORSaturday()) {
            price = price.multiply(new BigDecimal("1.1"));
        } else if (isChristmas()) {
            price = price.subtract(price.multiply(new BigDecimal("0.4")));
        } else if (isNewYear()) {
            price = price.subtract(price.multiply(new BigDecimal("0.4")));
        }
        return price;
    }

    private boolean isNewYear() {
        LocalDate flightDate = getFlightDate();
        return flightDate.getMonth() == Month.DECEMBER && flightDate.getDayOfMonth() == 31;
    }

    private boolean isChristmas() {
        LocalDate flightDate = getFlightDate();
        return flightDate.getMonth() == Month.DECEMBER && flightDate.getDayOfMonth() == 28;
    }

    private boolean isFridayORSaturday() {
        LocalDate flightDate = getFlightDate();
        return flightDate.getDayOfWeek() == DayOfWeek.FRIDAY || flightDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isTuesdayOrWednesdayORSaturday() {
        LocalDate flightDate = getFlightDate();
        return flightDate.getDayOfWeek() == DayOfWeek.TUESDAY || flightDate.getDayOfWeek() ==
                DayOfWeek.WEDNESDAY || flightDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    private boolean isEvening() {
        return flightDetail.getFlight().getArrival().isAfter(LocalTime.NOON) &&
                flightDetail.getFlight().getArrival().isBefore(LocalTime.of(21, 0));
    }

    private boolean isSummerSeason() {
        LocalDate flightDate = getFlightDate();
        return flightDate.getMonth() == Month.JUNE || flightDate.getMonth() == Month.JULY
                || flightDate.getMonth() == Month.AUGUST;
    }

    private boolean isFallOrWinter() {
        LocalDate flightDate = getFlightDate();
        return flightDate.getMonth() == Month.JANUARY || flightDate.getMonth() == Month.FEBRUARY ||
                flightDate.getMonth() == Month.MARCH || flightDate.getMonth() == Month.APRIL ||
                flightDate.getMonth() == Month.MAY || flightDate.getMonth() == Month.SEPTEMBER ||
                flightDate.getMonth() == Month.OCTOBER || flightDate.getMonth() == Month.NOVEMBER ||
                flightDate.getMonth() == Month.DECEMBER;
    }
}
