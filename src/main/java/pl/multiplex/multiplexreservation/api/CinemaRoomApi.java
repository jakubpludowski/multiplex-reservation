package pl.multiplex.multiplexreservation.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.multiplex.multiplexreservation.manager.CinemaRoomManager;

@RestController
@RequestMapping("multiplex/rooms")
public class CinemaRoomApi
{
    private CinemaRoomManager cinemaRoom;
}
