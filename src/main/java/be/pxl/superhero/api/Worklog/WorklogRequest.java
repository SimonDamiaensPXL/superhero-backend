package be.pxl.superhero.api.Worklog;

import javax.validation.constraints.NotBlank;

public class WorklogRequest {

    @NotBlank(message = "superheroName must not be blank")
    private String superheroName;

    @NotBlank(message = "Mission must not be blank")
    private String mission;

    @NotBlank(message = "Date must not be blank")
    private String date;

    @NotBlank(message = "Task must not be blank")
    private String task;

    public WorklogRequest(String superhero, String mission, String date, String task) {
        this.superheroName = superhero;
        this.mission = mission;
        this.date = date;
        this.task = task;
    }

    public WorklogRequest() {
    }

    public void setSuperheroName(String superheroName) {
        this.superheroName = superheroName;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSuperheroName() {
        return superheroName;
    }

    public String getMission() {
        return mission;
    }

    public String getDate() {
        return date;
    }

    public String getTask() {
        return task;
    }
}
