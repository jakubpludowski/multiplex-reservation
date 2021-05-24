package pl.multiplex.multiplexreservation.dao.entity;


import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Screening
{
    @javax.persistence.Id
    @SequenceGenerator(
            name = "screening_sequence",
            sequenceName = "screening_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "screening_sequence"
    )
    @Column(
            name = "screeningid"
    )
    private Long screeningId;
    private int date;
    private int time;
    @ManyToOne
    private Movie movieId;
    @OneToMany
    private Set<Reservation> reservations;
    @ManyToOne
    private CinemaRoom CinemaRoomId;




    public void addReservation(Reservation x)
    {
        if(!this.reservations.contains(x))
        {
            this.reservations.add(x);
            x.setScreeningId(this);
        }
    }

    public void removeReservation(Reservation x)
    {
        if(this.reservations.contains(x))
        {
            this.reservations.remove(x);
            x.setScreeningId(null);
        }
    }


    public Screening(int date,
                     int time,
                     Movie movieId,
                     Set<Reservation> reservations,
                     CinemaRoom cinemaRoomId) {
        this.date = date;
        this.time = time;
        this.movieId = movieId;
        this.reservations = reservations;
        CinemaRoomId = cinemaRoomId;
    }

    public Screening() {
    }

    public int getTime() { return time; }

    public void setTime(int time) {
        this.time = time;
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Long screeningId) {
        this.screeningId = screeningId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public CinemaRoom getCinemaRoomId() {
        return CinemaRoomId;
    }

    public void setCinemaRoomId(CinemaRoom id) {
        CinemaRoomId = id;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "screeningId=" + screeningId +
                ", date=" + date +
                ", movieId=" + movieId +
                ", Id=" + CinemaRoomId +
                '}';
    }
}
