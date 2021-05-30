package pl.multiplex.multiplexreservation.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.multiplex.multiplexreservation.dao.entity.Movie;
import pl.multiplex.multiplexreservation.dao.entity.Screening;
import pl.multiplex.multiplexreservation.dao.entity.Seat;
import pl.multiplex.multiplexreservation.dao.entity.User;
import pl.multiplex.multiplexreservation.manager.ReservationManager;

import java.util.List;

@RestController
@RequestMapping("multiplex/reservation")
public class ReservationApi
{
    private ReservationManager reservations;


    @Autowired
    public ReservationApi(ReservationManager reservation) {
        this.reservations = reservation;
    }

    @GetMapping("/screening/all")
    public Iterable<Screening> getAll() {return reservations.showAll();}

    @GetMapping("/screening")
    public List<Screening> getByDayAndTime(@RequestParam int date, int time) {
        return reservations.showByTimeAndDay(date,time);
    }

    @GetMapping("/screening/interval")
    public List<Screening> getByDayAndMinMaxTime(@RequestParam int date, int timeMin, int timeMax) {
        return reservations.showByTimeAndMinMaxDay(date,timeMin, timeMax);
    }

    @GetMapping("/screening/titles")
    public List<String> getTitlesByDayAndMinMaxTime(@RequestParam int date, int timeMin, int timeMax) {
        return reservations.showByTimeAndMinMaxDayOnlyTitle(date,timeMin, timeMax);
    }

    @GetMapping("/chooseseat")
    public List<Seat> getSeatsByScreeningId(@RequestParam Long screeningId) {
        List<Seat> seats = reservations.getSeatsByScreeningId(screeningId);
        return seats;
    }

    @PostMapping("/new")
    public void doReservation(@RequestParam Long userId, Long screeningId, Long seatId, String ticketType){
        reservations.postReservation(userId,screeningId,seatId,ticketType);
    }

    @PostMapping("/user")
    public void newUser(@RequestParam String name, String surname, String email){
        reservations.postUser(name,surname,email);
    }

}
