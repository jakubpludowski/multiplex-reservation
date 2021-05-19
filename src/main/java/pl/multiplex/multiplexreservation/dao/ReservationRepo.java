package pl.multiplex.multiplexreservation.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.multiplex.multiplexreservation.dao.entity.Reservation;

@Repository
public interface ReservationRepo extends CrudRepository<Reservation, Long> {
}
