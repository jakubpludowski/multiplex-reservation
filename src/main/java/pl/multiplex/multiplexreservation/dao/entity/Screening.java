package pl.multiplex.multiplexreservation.dao.entity;


import javax.persistence.*;
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
    private Date date;
    private int number_of_room;
    @ManyToOne
    private Movie movieId;
    @OneToMany
    private Set<Reservation> reservations;
    @ManyToOne
    private CinemaRoom Id;










    public Screening(Date date, int number_of_room, Movie movieId, Set<Reservation> reservations, CinemaRoom id) {
        this.date = date;
        this.number_of_room = number_of_room;
        this.movieId = movieId;
        this.reservations = reservations;
        Id = id;
    }

    public Screening() {
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Long screeningId) {
        this.screeningId = screeningId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber_of_room() {
        return number_of_room;
    }

    public void setNumber_of_room(int number_of_room) {
        this.number_of_room = number_of_room;
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

    public CinemaRoom getId() {
        return Id;
    }

    public void setId(CinemaRoom id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "screeningId=" + screeningId +
                ", date=" + date +
                ", number_of_room=" + number_of_room +
                ", movieId=" + movieId +
                ", Id=" + Id +
                '}';
    }
}
