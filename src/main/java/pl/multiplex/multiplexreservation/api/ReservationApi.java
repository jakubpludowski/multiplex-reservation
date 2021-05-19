package pl.multiplex.multiplexreservation.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.multiplex.multiplexreservation.dao.entity.Reservation;
import pl.multiplex.multiplexreservation.manager.ReservationManager;

@RestController
@RequestMapping("multiplex/reservation")
public class ReservationApi
{
    private ReservationManager reservation;

    @GetMapping("/all")
    public Iterable<Reservation> showAll(){ return reservation.showAll();}
}
