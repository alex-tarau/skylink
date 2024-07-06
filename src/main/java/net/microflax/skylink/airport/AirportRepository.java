package net.microflax.skylink.airport;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AirportRepository extends JpaRepository<Airport,Integer> {
}
