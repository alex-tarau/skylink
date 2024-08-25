package net.microflax.skylink.passenger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer>, JpaSpecificationExecutor<Passenger> {

    /**
     * Finds a passenger by its security username.
     *
     * @param userName the username
     * @return the passenger with that username
     */
    Optional<Passenger> findByUserName(String userName);

}
