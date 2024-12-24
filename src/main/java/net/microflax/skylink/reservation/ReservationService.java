package net.microflax.skylink.reservation;

import net.microflax.skylink.AbstractService;
import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airplane.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationService extends AbstractService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    /**
     * Update the total number of seats for each seat category on the airplane
     *
     * @param id   the id of the reservation
     * @param seat the seat type the passenger selects in the flight reservation
     */
    @Transactional
    public void updateAvailableSeats(int id, Reservation.Seat seat) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null) {
            Airplane airplane = reservation.getFlight().getAirplane();
            switch (seat) {
                case ECONOMY -> airplane.setEconomySeats(airplane.getEconomySeats() - 1);
                case ECONOMY_PLUS -> airplane.setEconomyPlusSeats(airplane.getEconomyPlusSeats() - 1);
                case BUSINESS -> airplane.setBusinessSeats(airplane.getBusinessSeats() - 1);
                case FIRST_CLASS -> airplane.setFirstClassSeats(airplane.getFirstClassSeats() - 1);
            }
            airplaneRepository.save(airplane);
        }
    }
}
