package pl.multiplex.multiplexreservation.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.multiplex.multiplexreservation.dao.CinemaRoomRepo;
import pl.multiplex.multiplexreservation.dao.entity.CinemaRoom;
import pl.multiplex.multiplexreservation.dao.entity.Movie;

@Service
public class CinemaRoomManager
{
    private CinemaRoomRepo cinemaRoomRepo;

    @Autowired
    public CinemaRoomManager(CinemaRoomRepo cinemaRoomRepo) {
        this.cinemaRoomRepo = cinemaRoomRepo;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void fillDataBase()
    {
        int number_of_days =3;
        int nr_of_movies_per_day = 3;
        Movie[] movies;
        movies = new Movie[nr_of_movies_per_day*number_of_days];
        String[] movies_names = new String[nr_of_movies_per_day];
        for(int i=0;i<nr_of_movies_per_day;i++)
        {movies_names[i] = "Movie nr "+ i;}

        int iterator =0;
        for(int i=0; i<number_of_days;i++)
        {
            int hour = 12;
            for(int j=0;j<nr_of_movies_per_day;j++)
            {
                movies[iterator] = new Movie(movies_names[j],2.5,i,hour);
                hour= hour+2;
            }
        }

        int number_of_rooms = 3;
        CinemaRoom[] cinemaRooms = new CinemaRoom[number_of_rooms];
        for(int i =0;i<number_of_rooms;i++)
        {
            cinemaRooms[i] = new CinemaRoom(i,3,5,0.3,4,3);
        }
    }
}
