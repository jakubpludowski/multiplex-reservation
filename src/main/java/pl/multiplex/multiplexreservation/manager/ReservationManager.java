package pl.multiplex.multiplexreservation.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.multiplex.multiplexreservation.dao.ReservationRepo;
import pl.multiplex.multiplexreservation.dao.entity.CinemaRoom;
import pl.multiplex.multiplexreservation.dao.entity.Movie;
import pl.multiplex.multiplexreservation.dao.entity.Reservation;

import java.util.Optional;
import java.util.Random;

@Service
public class ReservationManager
{

    private ReservationRepo reservationRepo;

    @Autowired
    public ReservationManager(ReservationRepo reservationRepo) {
        this.reservationRepo = reservationRepo;
    }

    public Reservation save(Reservation x){
        return reservationRepo.save(x);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDataBase()
    {


    }
}
