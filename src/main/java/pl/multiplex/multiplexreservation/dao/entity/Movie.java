package pl.multiplex.multiplexreservation.dao.entity;


import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Movie
{
    @Id
    @SequenceGenerator(
            name = "movie_sequence",
            sequenceName = "movie_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "movie_sequence"
    )
    @Column(
            name = "movieid"
    )
    private Long movieId;
    private String title;
    private double duration;


    @OneToMany
    private Set<Screening> screenings;



    public void addScreening(Screening x)
    {
        if(!this.screenings.contains(x))
        {
            this.screenings.add(x);
            x.setMovieId(this);
        }
    }

    public void removeScreening(Screening x)
    {
        if(this.screenings.contains(x))
        {
            this.screenings.remove(x);
            x.setMovieId(null);
        }
    }



    public Movie(String title,
                 double duration,
                 Set<Screening> screenings) {
        this.title = title;
        this.duration = duration;
        this.screenings = screenings;
    }

    public Movie() {
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
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

    public Set<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(Set<Screening> screenings) {
        this.screenings = screenings;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
