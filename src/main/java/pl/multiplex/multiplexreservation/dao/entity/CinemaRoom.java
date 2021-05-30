package pl.multiplex.multiplexreservation.dao.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
    @OneToMany
    private Set<Screening> screenings;
    @OneToMany
    private Set<Seat> seats;



    public void addScreening(Screening x) {
        if(!this.screenings.contains(x)) {
            this.screenings.add(x);
            x.setCinemaRoomId(this);
        }
    }

    public void removeScreening(Screening x) {
        if(this.screenings.contains(x)) {
            this.screenings.remove(x);
            x.setCinemaRoomId(null);
        }
    }

    public void addSeat(Seat x) {
        if(!this.seats.contains(x))
        {
            this.seats.add(x);
            x.setCinemaRoomId(this);
        }
    }

    public Seat getSeat(Long id) {
        Iterator seatIter = seats.iterator();
        while(seatIter.hasNext()){
            Seat seat =(Seat) seatIter.next();
            if(seat.getSeatId() == id){
                return seat;
            }
        }
        return null;
    }

    public void removeSeat(Seat x) {
        if(this.seats.contains(x)) {
            this.seats.remove(x);
            x.setCinemaRoomId(null);
        }
    }




    public CinemaRoom(
                      Set<Screening> screenings,
                      Set<Seat> seats) {
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
                '}';
    }
}
