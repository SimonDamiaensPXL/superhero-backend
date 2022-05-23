package be.pxl.superhero.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="superheroes")
public class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String superheroName;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Mission> missions = new ArrayList<>();

    public Superhero() {
        // JPA only
    }

    public Superhero(String firstName, String lastName, String superheroName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.superheroName = superheroName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuperheroName() {
        return superheroName;
    }

    public void setSuperheroName(String superheroName) {
        this.superheroName = superheroName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Superhero superhero = (Superhero) o;

        return id != null ? id.equals(superhero.id) : superhero.id == null;
    }

    public void addMission(Mission mission) {
        if (mission.isCompleted() || mission.isDeleted()) {
            throw new IllegalArgumentException("This mission was already completed or deleted.");
        }
        if (onMission(mission)) {
            throw new IllegalArgumentException("This mission was already assigned.");
        }
        missions.add(mission);
        mission.addSuperhero(this);
    }

    public boolean onMission(Mission mission) {
        return missions.stream().anyMatch(m -> m.getName().equals(mission.getName()));
    }

    public List<Mission> getMissions() {
        return missions;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
