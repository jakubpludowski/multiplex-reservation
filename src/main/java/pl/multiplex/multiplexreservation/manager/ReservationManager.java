package pl.multiplex.multiplexreservation.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.multiplex.multiplexreservation.dao.ReservationRepo;
import pl.multiplex.multiplexreservation.dao.entity.CinemaRoom;
import pl.multiplex.multiplexreservation.dao.entity.Movie;
import pl.multiplex.multiplexreservation.dao.entity.Reservation;

import java.util.Random;

@Service
public class ReservationManager
{

    private ReservationRepo reservationRepo;

    @Autowired
    public ReservationManager(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public Iterable<Reservation> showAll() {return reservationRepo.findAll();}

    public Reservation save(Reservation x){return reservationRepo.save(x);}

    @EventListener(ApplicationReadyEvent.class)
    public void fillDataBase()
    {
        int number_of_rooms = 3;
        int nr_of_movies_per_day = 3;
        int number_of_days = 3;
        int number_of_rows = 3;
        int nr_of_seats = 5;
        int duration = 190;
        String title;
        Long Id = 1L;
        Long showing_id = 1L;
        int ticket_price=0;
        String buyers_name="";
        String buyers_surname="";
        int max =100; int min = 1; int prob = 30;
        boolean x;

        String[] movies_names = new String[nr_of_movies_per_day];
        for(int i=0;i<nr_of_movies_per_day;i++)
        {movies_names[i] = "Movie nr "+ (i+1);}


        for(int i =0; i<number_of_rooms; i++)
        {
            for(int j=0;j< number_of_days;j++)
            {
                int hour = 12;
                for(int k=0;k<nr_of_movies_per_day;k++)
                {
                    title = movies_names[k];
                    for(int l=0; l<number_of_rows;l++)
                    {
                        for(int m=0; m<nr_of_seats; m++)
                        {
                            x=false;
                            Random random = new Random();
                            int draw = random.nextInt(max - min) + min;
                            if(draw<prob) {x=true;}
                            save(new Reservation(Id,i+1,showing_id,title,j,hour,duration,l+1,m+1,x,ticket_price,buyers_name,buyers_surname));
                            Id++;
                        }
                    }
                    hour= hour+2;
                    showing_id++;
                }
            }
        }

    }
}
