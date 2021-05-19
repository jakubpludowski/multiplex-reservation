package pl.multiplex.multiplexreservation.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seat
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private int nr_of_row;
    private int nr_of_seat;
    private boolean is_reserved;

    public Seat() {}

    public Seat(int nr_of_row, int nr_of_seat, boolean is_reserved) {
        this.nr_of_row = nr_of_row;
        this.nr_of_seat = nr_of_seat;
        this.is_reserved = is_reserved;
    }

    public int getNr_of_row() {
        return nr_of_row;
    }

    public void setNr_of_row(int nr_of_row) {
        this.nr_of_row = nr_of_row;
    }

    public int getNr_of_seat() {
        return nr_of_seat;
    }

    public void setNr_of_seat(int nr_of_seat) {
        this.nr_of_seat = nr_of_seat;
    }

    public boolean isIs_reserved() {
        return is_reserved;
    }

    public void setIs_reserved(boolean is_reserved) {
        this.is_reserved = is_reserved;
    }
}
