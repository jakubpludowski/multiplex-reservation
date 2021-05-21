package pl.multiplex.multiplexreservation.dao.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class CinemaRoom
{
    @Id
    @SequenceGenerator(
            name = "cinemaRoom_sequence",
            sequenceName = "cinemaRoom_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "cinemaRoom_sequence"
    )
    @Column(
            name = "cinemaRoomid"
    )
    private Long cinemaRoomId;
    private int nr_of_room;
    private int max_row;
    private int max_col;
    @OneToMany
    private Set<Screening> screenings;
    @OneToMany
    private Set<Seat> seats;



    public void addScreening(Screening x)
    {
        if(!this.screenings.contains(x))
        {
            this.screenings.add(x);
            x.setCinemaRoomId(this);
        }
    }

    public void removeScreening(Screening x)
    {
        if(this.screenings.contains(x))
        {
            this.screenings.remove(x);
            x.setCinemaRoomId(null);
        }
    }

    public void addSeat(Seat x)
    {
        if(!this.seats.contains(x))
        {
            this.seats.add(x);
            x.setCinemaRoomId(this);
        }
    }

    public void removeSeat(Seat x)
    {
        if(this.seats.contains(x))
        {
            this.seats.remove(x);
            x.setCinemaRoomId(null);
        }
    }




    public CinemaRoom(int nr_of_room,
                      int max_row,
                      int max_col,
                      Set<Screening> screenings,
                      Set<Seat> seats) {
        this.nr_of_room = nr_of_room;
        this.max_row = max_row;
        this.max_col = max_col;
        this.screenings = screenings;
        this.seats = seats;
    }

    public CinemaRoom() {
    }

    public Long getCinemaRoomId() {
        return cinemaRoomId;
    }

    public void setCinemaRoomId(Long cinemaRoomId) {
        this.cinemaRoomId = cinemaRoomId;
    }

    public int getNr_of_room() {
        return nr_of_room;
    }

    public void setNr_of_room(int nr_of_room) {
        this.nr_of_room = nr_of_room;
    }

    public int getMax_row() {
        return max_row;
    }

    public void setMax_row(int max_row) {
        this.max_row = max_row;
    }

    public int getMax_col() {
        return max_col;
    }

    public void setMax_col(int max_col) {
        this.max_col = max_col;
    }

    public Set<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(Set<Screening> screenings) {
        this.screenings = screenings;
    }

    public Set<Seat> getSeats() {
        return seats;
    }

    public void setSeats(Set<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "CinemaRoom{" +
                "cinemaRoomId=" + cinemaRoomId +
                ", nr_of_room=" + nr_of_room +
                ", max_row=" + max_row +
                ", max_col=" + max_col +
                '}';
    }
}
