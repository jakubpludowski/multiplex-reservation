package pl.multiplex.multiplexreservation.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.multiplex.multiplexreservation.dao.entity.Movie;


@Repository
public interface MovieRepo extends CrudRepository<Movie, Long> {
}
