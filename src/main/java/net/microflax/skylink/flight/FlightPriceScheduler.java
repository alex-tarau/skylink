package net.microflax.skylink.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
class FlightPriceScheduler implements Runnable {

    @Autowired
    private FlightDetailRepository flightDetailRepository;

    @Override
    public void run() {
        List<FlightDetail> flightDetails = flightDetailRepository.findAll();
        flightDetails.forEach(flightDetail -> {
            if (flightDetail.getPrice().equals(BigDecimal.ONE)) {
                flightDetail.setPrice(BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(200, 850)));
            }
            validate(flightDetail, flightDetail.getFlightDate());
            flightDetailRepository.save(flightDetail);
        });
    }

    private void validate(FlightDetail flightDetail, LocalDate flightDate) {
        LocalDate todayDate = LocalDate.now();
        if (todayDate.isBefore(flightDate)) {
            if ((flightDate.getMonthValue() - todayDate.getMonthValue()) < 3) {
                flightDetail.setPrice(flightDetail.getPrice().subtract(flightDetail.getPrice().
                        multiply(new BigDecimal("0.15"))));
            }
        }
        if (isSummerSeason(flightDate)) {
            flightDetail.setPrice(flightDetail.getPrice().multiply(new BigDecimal("1.3")));
        } else if (isFallOrWinter(flightDate)) {
            flightDetail.setPrice(flightDetail.getPrice().subtract(flightDetail.getPrice().
                    multiply(new BigDecimal("0.25"))));
        } else if (isEvening(flightDetail)) {
            flightDetail.setPrice(flightDetail.getPrice().subtract(flightDetail.getPrice().
                    multiply(new BigDecimal("0.2"))));
        } else if (isTuesdayOrWednesdayORSaturday(flightDate)) {
            flightDetail.setPrice(flightDetail.getPrice().subtract(flightDetail.getPrice().multiply(new BigDecimal("0.1"))));
        } else if (isFridayORSaturday(flightDate)) {
            flightDetail.setPrice(flightDetail.getPrice().multiply(new BigDecimal("1.1")));
        } else if (isChristmas(flightDate)) {
            flightDetail.setPrice(flightDetail.getPrice().subtract(flightDetail.getPrice().multiply(new BigDecimal("0.4"))));
        } else if (isNewYear(flightDate)) {
            flightDetail.setPrice(flightDetail.getPrice().subtract(flightDetail.getPrice().multiply(new BigDecimal("0.4"))));
        }
    }

    private boolean isNewYear(LocalDate flightDate) {
        return flightDate.getMonth() == Month.DECEMBER && flightDate.getDayOfMonth() == 31;
    }

    private boolean isChristmas(LocalDate flightDate) {
        return flightDate.getMonth() == Month.DECEMBER && flightDate.getDayOfMonth() == 28;
    }

    private boolean isFridayORSaturday(LocalDate flightDate) {
        return flightDate.getDayOfWeek() == DayOfWeek.FRIDAY || flightDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private static boolean isTuesdayOrWednesdayORSaturday(LocalDate flightDate) {
        return flightDate.getDayOfWeek() == DayOfWeek.TUESDAY || flightDate.getDayOfWeek() ==
                DayOfWeek.WEDNESDAY || flightDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    private boolean isEvening(FlightDetail flightDetail) {
        return flightDetail.getFlight().getArrival().isAfter(LocalTime.NOON) &&
                flightDetail.getFlight().getArrival().isBefore(LocalTime.of(21, 0));
    }

    private boolean isSummerSeason(LocalDate flightDate) {
        return flightDate.getMonth() == Month.JUNE || flightDate.getMonth() == Month.JULY || flightDate.getMonth()
                == Month.AUGUST;
    }

    private boolean isFallOrWinter(LocalDate flightDate) {
        return flightDate.getMonth() == Month.JANUARY || flightDate.getMonth() == Month.FEBRUARY ||
                flightDate.getMonth() == Month.MARCH || flightDate.getMonth() == Month.APRIL ||
                flightDate.getMonth() == Month.MAY || flightDate.getMonth() == Month.SEPTEMBER ||
                flightDate.getMonth() == Month.OCTOBER || flightDate.getMonth() == Month.NOVEMBER ||
                flightDate.getMonth() == Month.DECEMBER;
    }
}
