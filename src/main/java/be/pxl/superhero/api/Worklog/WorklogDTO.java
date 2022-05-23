package be.pxl.superhero.api.Worklog;

import be.pxl.superhero.api.Superhero.SuperheroDTO;
import be.pxl.superhero.domain.Mission;
import be.pxl.superhero.domain.Superhero;
import be.pxl.superhero.domain.Worklog;

import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

public class WorklogDTO {

    private Long id;

    private Superhero superhero;

    private Mission mission;

    private LocalDate date;

    private String task;

    public WorklogDTO(Worklog worklog) {
        this.id = worklog.getId();
        this.superhero = worklog.getSuperhero();
        this.mission = worklog.getMission();
        this.date = worklog.getDate();
        this.task = worklog.getTask();
    }
}
