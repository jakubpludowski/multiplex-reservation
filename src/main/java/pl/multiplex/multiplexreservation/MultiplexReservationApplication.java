package pl.multiplex.multiplexreservation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.multiplex.multiplexreservation.dao.*;
import pl.multiplex.multiplexreservation.dao.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class MultiplexReservationApplication {

    public static void main(String[] args) {

        SpringApplication.run(MultiplexReservationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            ReservationRepo reservationRepo,
            MovieRepo movieRepo,
            CinemaRoomRepo cinemaRoomRepo,
            ScreeningRepo screeningRepo,
            SeatRepo seatRepo,
            UserRepo userRepo) {
        return args -> {

            Set<Screening> screenings = null;
            Movie[] movies = null;
            Set<CinemaRoom> cinemaRooms = null;
            Set<Reservation> reservations = null;
            Set<Seat> seats = null;
            Set<User> users = null;

            int nr_of_movies = 3;
            String[] movies_names = new String[3];
            double[] durations = new double[3];
            for(int i =0;i<nr_of_movies;i++)
            {
                movies_names[i] = "Movie nr " +(i+1);
                if(i%2 ==0)
                { durations[i] = 2 + (i/10); } else {
                    durations[i] = 2 - (i/10);
                }
                movies[i] = (new Movie(movies_names[i],durations[i],screenings));
            }


            int mr = 2; int ms = 5;
            int nr_of_rooms = 3;
            for(int j =0;j<mr;j++)
            {
                for(int k =0;k<ms;k++)
                {
                    seats.add(new Seat(j+1,k+1,null,reservations));
                }
            }


            for(int i =0;i<nr_of_rooms;i++)
            {
                cinemaRooms.add(new CinemaRoom(i,mr,ms,screenings,seats));
            }

            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.of(12,0);
            int nr_of_days = 2;
            int[] days =new int[nr_of_days];
            for(int i=0;i<nr_of_days; i++)
            {
                days[i] = date.plusDays(i).getDayOfMonth();
            }

            for(int i=0;i<nr_of_rooms;i++)
            {
                int index = i;
                Optional<CinemaRoom> first =  cinemaRooms.stream().
                filter(element -> element.getCinemaRoomId()==index).findFirst();

                for(int j= 0;j<nr_of_days;j++)
                {
                    for(int k=0;k<nr_of_movies; k++)
                    {
                        screenings.add(new Screening(date.plusDays(j+1),time,movies[k],reservations, first.get()));
                        time = time.plusHours(2);
                    }
                }

            }
            for(int i=0; i<nr_of_movies;i++)
            {
                movieRepo.save(movies[i]);
            }










        };
    }

}
