package pl.multiplex.multiplexreservation.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Entity
public class CinemaRoom
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int number;
    private int number_of_rows;
    private int number_of_seats_in_row;
    private Seat[][] seats;
    private Movie[] movies;

    public CinemaRoom() {
    }

    public CinemaRoom(int number, int number_of_rows, int number_of_seats_in_row, double prob, int nr_of_movies_per_day, int number_of_days)
    {
        this.number = number;
        this.number_of_rows = number_of_rows;
        this.number_of_seats_in_row = number_of_seats_in_row;

        seats = new Seat[number_of_rows][number_of_seats_in_row];
        int max = 100; int min = 1;
        prob = prob*100;
        boolean x = false;
        for (int i=0;i<number_of_rows; i++)
        {
            for (int j=0;j<number_of_seats_in_row; j++)
            {
                Random random = new Random();
                int draw = random.nextInt(max - min) + min;
                if(draw<prob) {x=true;}
                seats[i][j] = new Seat(i, j, x);
            }
        }

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




    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber_of_rows() {
        return number_of_rows;
    }

    public void setNumber_of_rows(int number_of_rows) {
        this.number_of_rows = number_of_rows;
    }

    public int getNumber_of_seats_in_row() {
        return number_of_seats_in_row;
    }

    public void setNumber_of_seats_in_row(int number_of_seats_in_row) {
        this.number_of_seats_in_row = number_of_seats_in_row;
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public void setSeats(Seat[][] seats) {
        this.seats = seats;
    }

    public Movie[] getMovies() {
        return movies;
    }

    public void setMovies(Movie[] movies) {
        this.movies = movies;
    }
}
