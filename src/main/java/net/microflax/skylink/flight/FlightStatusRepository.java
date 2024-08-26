package net.microflax.skylink.flight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightStatusRepository extends JpaRepository<FlightStatus, FlightStatus.Id>, JpaSpecificationExecutor<FlightStatus> {

    /**
     * Find by id of the flight status, which composes of the flight and the date of the flight
     *
     * @param flightStatusId the id of the flight status
     * @return
     */
    Optional<FlightStatus> findByFlightAndFlightDate(FlightStatus.Id flightStatusId);
}
