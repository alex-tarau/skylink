package net.microflax.skylink.flight;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
