package pl.multiplex.multiplexreservation.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.multiplex.multiplexreservation.dao.entity.Reservation;
import pl.multiplex.multiplexreservation.dao.entity.Screening;
import pl.multiplex.multiplexreservation.manager.ReservationManager;
import pl.multiplex.multiplexreservation.manager.ScreeningManager;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("multiplex/reservation")
public class ReservationApi
{
    private ReservationManager reservations;
    private ScreeningManager screenings;

    @Autowired
    public ReservationApi(ReservationManager reservation, ScreeningManager screening) {
        this.reservations = reservation;
        this.screenings = screening;
    }

    @GetMapping("/screening/all")
    public Iterable<Screening> getAll() {return screenings.showAll();}

    @GetMapping("/screening")
    public List<Screening> getByDayAndTime(@RequestParam int date, int time) {
        return screenings.showByTimeAndDay(date,time);
    }

    @GetMapping("/screening/interval")
    public List<Screening> getByDayAndMinMaxTime(@RequestParam int date, int timeMin, int timeMax) {
        return screenings.showByTimeAndMinMaxDay(date,timeMin, timeMax);
    }

}
