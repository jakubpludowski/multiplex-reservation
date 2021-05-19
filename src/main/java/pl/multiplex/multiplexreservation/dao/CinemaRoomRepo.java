package pl.multiplex.multiplexreservation.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.multiplex.multiplexreservation.dao.entity.CinemaRoom;

@Repository
public interface CinemaRoomRepo extends CrudRepository<CinemaRoom, Long> {
}
