package pl.multiplex.multiplexreservation.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.multiplex.multiplexreservation.dao.entity.CinemaRoom;
import pl.multiplex.multiplexreservation.dao.entity.Reservation;

public interface CinemaRoomRepo extends PagingAndSortingRepository<CinemaRoom, Long> {
}
