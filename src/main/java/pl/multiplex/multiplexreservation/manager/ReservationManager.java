package pl.multiplex.multiplexreservation.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.multiplex.multiplexreservation.dao.*;
import pl.multiplex.multiplexreservation.dao.entity.*;

import java.util.*;

@Service
public class ReservationManager
{

    private ReservationRepo reservationRepo;
    private ScreeningRepo screeningRepo;
    private MovieRepo movieRepo;
    private SeatRepo seatRepo;
    private UserRepo userRepo;

    @Autowired
    public ReservationManager(ReservationRepo reservationRepo, ScreeningRepo screeningRepo, MovieRepo movieRepo, SeatRepo seatRepo, UserRepo userRepo) {
        this.reservationRepo = reservationRepo;
        this.screeningRepo = screeningRepo;
        this.movieRepo = movieRepo;
        this.seatRepo = seatRepo;
        this.userRepo = userRepo;
    }




    public Iterable<Screening> showAll() {return screeningRepo.findAll();}

    public List<Screening> showByTimeAndDay(int date, int time){
        return screeningRepo.findScreeningByTimeAndDate(date,time);
    }
    public List<String> showByTimeAndMinMaxDayOnlyTitle(int date, int timeMin, int timeMax) {
        List<Screening> availableScreenings =  screeningRepo.findScreeningByMinMaxTimeAndDate(date, timeMin, timeMax);
        Set<String> availableMovies = new HashSet<String>();
        List<String> moviesList = new LinkedList<String>();
        Screening scr;
        Iterator screenIter = availableScreenings.iterator();
        while(screenIter.hasNext()){
            scr = (Screening) screenIter.next();
            if(!availableMovies.contains(scr.getMovieId())){
                availableMovies.add(scr.getMovieId().getTitle());
            }
        }

        if(!availableMovies.isEmpty()){
            Iterator<String> moviesIter = availableMovies.iterator();
            while(moviesIter.hasNext()){
                moviesList.add(moviesIter.next());
            }
        }

        Collections.sort(moviesList);
        return moviesList;
    }

    public List<Screening> showByTimeAndMinMaxDay(int date, int timeMin, int timeMax) {
        List<Screening> availableScreenings =  screeningRepo.findScreeningByMinMaxTimeAndDate(date, timeMin, timeMax);
        return availableScreenings;
    }

    public List<Seat> getSeatsByScreeningId(Long screeningId){
        List<Seat>seats = new LinkedList<Seat>();
        Screening scr = screeningRepo.findScreeningById(screeningId);
        if(!(scr == null)){
            Set<Seat> seatsSet = scr.getCinemaRoomId().getSeats();
            Iterator<Seat> seatIter = seatsSet.iterator();
            while(seatIter.hasNext()){
                seats.add(seatIter.next());
            }
            List<Reservation> reservedSeats = reservationRepo.getReservedSeats(screeningId);
            Iterator<Reservation> reservationsIter = reservedSeats.iterator();
            while(reservationsIter.hasNext()){
                Seat reservedSeat = reservationsIter.next().getSeatId();
                if(seats.contains(reservedSeat)){
                    seats.remove(reservedSeat);
                }
            }
        }
        return seats;
    }

    public void postReservation(Long userId, Long screeningId, Long seatId, String ticketType){
        User user = userRepo.findUserById(userId);
        Screening scr = screeningRepo.findScreeningById(screeningId);
        Seat seat = scr.getCinemaRoomId().getSeat(seatId);
        double price = 0;
        boolean correct = false;
        if(ticketType.equals("adult")){
            price = 25;
            correct = true;
        }
        else if(ticketType.equals("student")){
            price = 18;
            correct = true;
        }
        else if(ticketType.equals("child")){
            price = 12.5;
            correct = true;
        }
        else {System.out.println("Podaj wlasciwy typ biletu");}

        if(correct){
            List <Seat> freeSeats = getSeatsByScreeningId(screeningId);
            if(freeSeats.contains(seat)){
                Reservation newReservation = new Reservation(price, user, scr, seat);
                reservationRepo.save(newReservation);
                System.out.println("Dodano nową rezerwację");
            }
            else{
                System.out.println("To miejsce jest już zajęte");
            }
        }
    }
    public void postUser(String name, String surname, String email){
        userRepo.save(new User(name, surname,email));
    }

    public String getExpirationTime(Long userId) {
        String time = null;
        int date = 0;
        double hour = 0;
        List<Reservation> res = reservationRepo.getUser(userId);
        Iterator resIter =  res.iterator();
        while(resIter.hasNext()){
            if(date == 0){
                Reservation thisRes =(Reservation) resIter.next();
                date =  thisRes.getScreeningId().getDate();
                hour = thisRes.getScreeningId().getTime();
            }
            else {
                Reservation thisRes =(Reservation) resIter.next();
                if(date>= thisRes.getScreeningId().getDate()){
                    date = thisRes.getScreeningId().getDate();
                    if(hour >=thisRes.getScreeningId().getTime() ){
                        hour = thisRes.getScreeningId().getTime();
                    }
                }
            }
        }
        hour = hour - 1 + 0.45;
        time = "Dzień: " + date +". Godzina: "+hour+".";
        return time;
    }

    public double getAmountToPay(Long userId){
        double money = 0;
        List<Reservation> res = reservationRepo.getUser(userId);
        Iterator resIter =  res.iterator();
        while(resIter.hasNext()){
            Reservation thisRes =(Reservation) resIter.next();
            money += thisRes.getTicket_type();
        }
        return money;
    }
}
