package pl.multiplex.multiplexreservation.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.multiplex.multiplexreservation.dao.ScreeningRepo;
import pl.multiplex.multiplexreservation.dao.entity.Screening;

import java.util.List;
import java.util.Optional;
@Service
public class ScreeningManager {

    private ScreeningRepo screeningRepo;

    @Autowired
    public ScreeningManager(ScreeningRepo screeningRepo) {
        this.screeningRepo = screeningRepo;
    }

    public Iterable<Screening> showAll() {return screeningRepo.findAll();}

    public List<Screening> showByTimeAndDay(int date, int time){
        return screeningRepo.findScreeningByTimeAndDate(date,time);
        }
    public List<Screening> showByTimeAndMinMaxDay(int date, int timeMin, int timeMax){
        return screeningRepo.findScreeningByMinMaxTimeAndDate(date,timeMin,timeMax);
    }
}
