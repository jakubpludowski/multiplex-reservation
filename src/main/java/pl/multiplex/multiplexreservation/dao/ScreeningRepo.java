package pl.multiplex.multiplexreservation.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.multiplex.multiplexreservation.dao.entity.Reservation;
import pl.multiplex.multiplexreservation.dao.entity.Screening;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface ScreeningRepo extends PagingAndSortingRepository<Screening, Long> {
    @Query(
            value = "SELECT * FROM SCREENING WHERE DATE = :date AND TIME >= :time",
            nativeQuery = true)
    List<Screening> findScreeningByTimeAndDate(int date, int time);


    @Query(
            value = "SELECT * FROM SCREENING WHERE DATE = :date AND TIME >= :timeMin AND TIME <= :timeMax",
            nativeQuery = true)
    List<Screening> findScreeningByMinMaxTimeAndDate(int date, int timeMin, int timeMax);
}
