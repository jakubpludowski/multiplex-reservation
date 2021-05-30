package pl.multiplex.multiplexreservation.dao.entity;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class User
{

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "userId"
    )
    private Long userId;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String surname;

    @Column(
            name = "email",
            columnDefinition = "TEXT"
    )
    private String email;


    private double amount_to_pay;
    private int amount_of_res;

    @OneToMany
    private Set<Reservation> reservations;

    public void addReservation(Reservation x) {
        if(!this.reservations.contains(x)) {
            this.reservations.add(x);
            x.setUserId(this);
        }
    }

    public void removeReservation(Reservation x) {
        if(this.reservations.contains(x)) {
            this.reservations.remove(x);
            x.setUserId(null);
        }
    }




    public User(String name,
                String surname,
                String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public User() {
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAmount_to_pay() {
        return amount_to_pay;
    }

    public void setAmount_to_pay(double amount_to_pay) {
        this.amount_to_pay = amount_to_pay;
    }

    public int getAmount_of_res() {
        return amount_of_res;
    }

    public void setAmount_of_res(int amount_of_res) {
        this.amount_of_res = amount_of_res;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", amount_to_pay=" + amount_to_pay +
                ", amount_of_res=" + amount_of_res +
                '}';
    }
}
