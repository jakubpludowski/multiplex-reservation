package pl.multiplex.multiplexreservation.dao.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long Id;
    private int nr_of_room;
    private Long id_of_movie;
    private String movie_title;
    private int day;
    private int hour;
    private int duration;
    private int nr_row;
    private int seat_nr;
    private boolean is_reserved;
    private int ticket_price;
    private String buyers_name;
    private String buyers_surname;

    public Reservation() {
    }

    public Reservation(Long id, int nr_of_room, Long id_of_movie, String movie_title, int day, int hour, int duration, int nr_row, int seat_nr, boolean is_reserved, int ticket_price, String buyers_name, String buyers_surname) {
        Id = id;
        this.nr_of_room = nr_of_room;
        this.id_of_movie = id_of_movie;
        this.movie_title = movie_title;
        this.day = day;
        this.hour = hour;
        this.duration = duration;
        this.nr_row = nr_row;
        this.seat_nr = seat_nr;
        this.is_reserved = is_reserved;
        this.ticket_price = ticket_price;
        this.buyers_name = buyers_name;
        this.buyers_surname = buyers_surname;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getNr_of_room() {
        return nr_of_room;
    }

    public void setNr_of_room(int nr_of_room) {
        this.nr_of_room = nr_of_room;
    }

    public Long getId_of_movie() {
        return id_of_movie;
    }

    public void setId_of_movie(Long id_of_movie) {
        this.id_of_movie = id_of_movie;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNr_row() {
        return nr_row;
    }

    public void setNr_row(int nr_row) {
        this.nr_row = nr_row;
    }

    public int getSeat_nr() {
        return seat_nr;
    }

    public void setSeat_nr(int seat_nr) {
        this.seat_nr = seat_nr;
    }

    public boolean isIs_reserved() {
        return is_reserved;
    }

    public void setIs_reserved(boolean is_reserved) {
        this.is_reserved = is_reserved;
    }

    public int getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(int ticket_price) {
        this.ticket_price = ticket_price;
    }

    public String getBuyers_name() {
        return buyers_name;
    }

    public void setBuyers_name(String buyers_name) {
        this.buyers_name = buyers_name;
    }

    public String getBuyers_surname() {
        return buyers_surname;
    }

    public void setBuyers_surname(String buyers_surname) {
        this.buyers_surname = buyers_surname;
    }
}

