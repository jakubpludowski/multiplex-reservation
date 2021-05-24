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
import java.util.*;

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

            Set<Screening> screenings= new HashSet<Screening>();
            Set<Movie> movies= new HashSet<Movie>();
            Set<CinemaRoom> cinemaRooms= new HashSet<CinemaRoom>();
            Set<Reservation> reservations= new HashSet<Reservation>();
            Set<User> users= new HashSet<User>();
            Set<Seat> seats= new HashSet<Seat>();

            movies = generateMovies(3);
            cinemaRooms = generateCinemaRooms(2,2,3,seatRepo);
            screenings = generateScreenings(3,cinemaRooms,movies);

            movieRepo.saveAll(movies);
            cinemaRoomRepo.saveAll(cinemaRooms);
            screeningRepo.saveAll(screenings);


        };
    }

    public Set<Movie> generateMovies(int nrOfMovies) {
        Set<Movie> movies = new HashSet<Movie>();
        Set<Screening> screenings = new HashSet<Screening>();           //to add
        String[] movies_names = new String[3];
        double[] durations = new double[3];
        for(int i =0;i<nrOfMovies;i++)
        {
            movies_names[i] = "Movie nr " +(i+1);
            if(i%2 ==0)
            { durations[i] = 2 + ((double)i/10); } else {
                durations[i] = 2 - ((double)i/10);
            }
            movies.add(new Movie(movies_names[i],durations[i],screenings));
        }
        return movies;
    }

    public Set<Seat> generateSeats(int nrOfRows,int nrOfColumns){
        Set<Reservation> reservations = new HashSet<Reservation>();                   //to add
        Set<Seat> seats =  new HashSet<Seat>();
        for(int j =0;j<nrOfRows;j++) {
            for (int k = 0; k < nrOfColumns; k++) {
                seats.add(new Seat(j + 1, k + 1, null, reservations));
            }
        }
        return  seats;
    }

    public Set<CinemaRoom> generateCinemaRooms(int nrOfRooms,int nrOfRows,int nrOfColumns,SeatRepo seatRepo){
        Set<Screening> screenings = new HashSet<Screening>();           //to add
        Set<CinemaRoom> cinemaRooms = new HashSet<CinemaRoom>();
        for(int i =0;i<nrOfRooms;i++)
        {
            Set<Seat> seats = null;
            seats = generateSeats(nrOfRows, nrOfColumns);
            seatRepo.saveAll(seats);
            cinemaRooms.add(new CinemaRoom(screenings,seats));
        }
    return cinemaRooms;
    }

    public Set<Screening> generateScreenings(int nrOfDays, Set<CinemaRoom> cinemaRooms, Set<Movie> movies){
        Set<Screening> screenings = new HashSet<Screening>();
        Set<Reservation> reservations = new HashSet<Reservation>();           //to add
        int nrOfMoviesPerDay = movies.size();

        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.of(12,0);
        int[] days =new int[nrOfDays];
        int[] hours =new int[nrOfMoviesPerDay];
        for(int i=0;i<nrOfDays; i++)
        {
            days[i] = date.plusDays(i+1).getDayOfMonth();
        }
        for(int i=0;i<nrOfMoviesPerDay; i++)
        {
            hours[i] = time.plusHours((3*i)).getHour();
        }



        Iterator<CinemaRoom> roomIter = cinemaRooms.iterator();
        while(roomIter.hasNext()) {
            CinemaRoom room = roomIter.next();
            for (int j = 0; j < nrOfDays; j++) {
                Iterator<Movie> movieIter = movies.iterator();
                int hourIter = 0;
                while (movieIter.hasNext()) {
                    Movie movie = movieIter.next();
                    Screening screening = new Screening(days[j], hours[hourIter], movie, reservations, room);
                    screenings.add(screening);
                    hourIter++;
                }
            }
        }



        return screenings;
    }

}
