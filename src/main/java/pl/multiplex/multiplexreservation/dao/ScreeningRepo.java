package pl.multiplex.multiplexreservation.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.multiplex.multiplexreservation.dao.entity.Reservation;
import pl.multiplex.multiplexreservation.dao.entity.Screening;

public interface ScreeningRepo extends PagingAndSortingRepository<Screening, Long> {
}
