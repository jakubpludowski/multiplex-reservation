package pl.multiplex.multiplexreservation.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.multiplex.multiplexreservation.dao.entity.Reservation;
import pl.multiplex.multiplexreservation.dao.entity.Seat;

public interface SeatRepo extends PagingAndSortingRepository<Seat, Long> {
}
