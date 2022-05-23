package be.pxl.superhero.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Worklog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Superhero superhero;

    @ManyToOne
    private Mission mission;

    private LocalDate date;

    private String task;

    public Worklog() {
        // JPA only
    }

    public Long getId() {
        return id;
    }

    public Superhero getSuperhero() {
        return superhero;
    }

    public void setSuperhero(Superhero superhero) {
        this.superhero = superhero;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
