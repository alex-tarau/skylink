package net.microflax.skylink.reservation;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
