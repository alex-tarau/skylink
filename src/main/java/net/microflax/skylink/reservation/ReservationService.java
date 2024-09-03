package net.microflax.skylink.reservation;

import net.microflax.skylink.AbstractService;
import net.microflax.skylink.airplane.Airplane;
import net.microflax.skylink.airplane.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReservationService extends AbstractService<Reservation,Integer> {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    protected JpaRepository<Reservation, Integer> getRepository() {
        return reservationRepository;
    }

    @Override
    protected Reservation preSave(Reservation reservation) {
        updateAvailableSeats(reservation.getId(), reservation.getSeat());
        return reservation;
    }


    /**
     * Update the total number of seats for each seat category on the airplane
     *
     * @param id   the id of the reservation
     * @param seat the seat type the passenger selects in the flight reservation
     */
    private void updateAvailableSeats(int id, Reservation.Seat seat) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Airplane airplane = optionalReservation.get().getFlight().getAirplane();
            switch (seat) {
                case ECONOMY:
                    airplane.setEconomySeats(airplane.getEconomySeats() - 1);
                    break;
                case ECONOMY_PLUS:
                    airplane.setEconomyPlusSeats(airplane.getEconomyPlusSeats() - 1);
                    break;
                case BUSINESS:
                    airplane.setBusinessSeats(airplane.getBusinessSeats() - 1);
                    break;
                case FIRST_CLASS:
                    airplane.setFirstClassSeats(airplane.getFirstClassSeats() - 1);
                    break;
            }
            airplaneRepository.save(airplane);
        } else {
            throw new NoSuchElementException("This reservation does not exist in the database");
        }
    }
}
