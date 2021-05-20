package pl.multiplex.multiplexreservation.dao.entity;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Seat
{
    @Id
    @SequenceGenerator(
            name = "seat_sequence",
            sequenceName = "seat_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "seat_sequence"
    )
    @Column(
            name = "seatId"
    )
    private Long seatId;
    private int nr_row;
    private int nr_seat;
    @ManyToOne
    private CinemaRoom cinemaRoomId;
    @OneToMany
    private Set<Reservation> reservations;







    public Seat(int nr_row, int nr_seat, CinemaRoom cinemaRoomId, Set<Reservation> reservations) {
        this.nr_row = nr_row;
        this.nr_seat = nr_seat;
        this.cinemaRoomId = cinemaRoomId;
        this.reservations = reservations;
    }

    public Seat() {
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public int getNr_row() {
        return nr_row;
    }

    public void setNr_row(int nr_row) {
        this.nr_row = nr_row;
    }

    public int getNr_seat() {
        return nr_seat;
    }

    public void setNr_seat(int nr_seat) {
        this.nr_seat = nr_seat;
    }

    public CinemaRoom getCinemaRoomId() {
        return cinemaRoomId;
    }

    public void setCinemaRoomId(CinemaRoom cinemaRoomId) {
        this.cinemaRoomId = cinemaRoomId;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", nr_row=" + nr_row +
                ", nr_seat=" + nr_seat +
                ", cinemaRoomId=" + cinemaRoomId +
                '}';
    }
}
