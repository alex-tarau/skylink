package net.microflax.skylink.airline;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
}
