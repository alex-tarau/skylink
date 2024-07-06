package net.microflax.skylink.passenger;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PassengerRepository extends JpaRepository<Passenger,Integer> {
}
