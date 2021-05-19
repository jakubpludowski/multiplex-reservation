package pl.multiplex.multiplexreservation.dao.entity;

public class Movie
{
    private String title;
    private double duration;
    private int day;
    private int hour;

    public Movie() {
    }

    public Movie(String title, double duration, int day, int hour) {
        this.title = title;
        this.duration = duration;
        this.day = day;
        this.hour = hour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
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
}