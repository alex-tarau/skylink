package net.microflax.skylink.flight;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
