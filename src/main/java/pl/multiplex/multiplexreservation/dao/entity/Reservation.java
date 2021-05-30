package pl.multiplex.multiplexreservation.dao.entity;


import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Reservation
{
    @Id
    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "reservation_sequence"
    )
    @Column(
            name = "reservationId"
    )
    private Long reservationId;


    private double ticket_type;

    @ManyToOne
    private User userId;
    @ManyToOne
    private Screening screeningId;
    @ManyToOne
    private Seat seatId;

    public Reservation(double ticket_type,
                       User userId,
                       Screening screeningId,
                       Seat seatId) {
        this.ticket_type = ticket_type;
        this.userId = userId;
        this.screeningId = screeningId;
        this.seatId = seatId;
    }

    public Reservation() {
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public double getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(double ticket_type) {
        this.ticket_type = ticket_type;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Screening getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Screening screeningId) {
        this.screeningId = screeningId;
    }

    public Seat getSeatId() {
        return seatId;
    }

    public void setSeatId(Seat seatId) {
        this.seatId = seatId;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", ticket_type=" + ticket_type +
                ", userId=" + userId +
                ", screeningId=" + screeningId +
                ", seatId=" + seatId +
                '}';
    }
}

