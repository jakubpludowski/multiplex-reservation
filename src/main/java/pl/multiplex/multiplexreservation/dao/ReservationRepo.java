package pl.multiplex.multiplexreservation.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.multiplex.multiplexreservation.dao.entity.Reservation;
import pl.multiplex.multiplexreservation.dao.entity.Screening;
import pl.multiplex.multiplexreservation.dao.entity.Seat;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ReservationRepo extends PagingAndSortingRepository<Reservation, Long> {

    @Query(
            value = "SELECT * FROM RESERVATION WHERE  SCREENING_ID_SCREENINGID  = :Id ",
            nativeQuery = true)
    List<Reservation> getReservedSeats(Long Id);
}
