package pl.multiplex.multiplexreservation.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.multiplex.multiplexreservation.dao.entity.Reservation;
import pl.multiplex.multiplexreservation.dao.entity.Screening;
import pl.multiplex.multiplexreservation.dao.entity.User;


@Repository
@Transactional(readOnly = true)
public interface UserRepo extends PagingAndSortingRepository<User, Long> {
    @Query(
            value = "SELECT * FROM USER WHERE USER_ID  = :Id ",
            nativeQuery = true)
    User findUserById(Long Id);
}
