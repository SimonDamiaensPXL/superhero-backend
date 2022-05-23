package be.pxl.superhero.api.Superhero;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class AddSuperheroToMissionRequest {

    @NotBlank(message = "missionId must not be blank")
    private final Long missionId;

    @NotBlank(message = "superheroId must not be blank")
    private final Long superheroId;

    @JsonCreator
    public AddSuperheroToMissionRequest(Long missionId, Long superheroId) {
        this.missionId = missionId;
        this.superheroId = superheroId;
    }

    public Long getMissionId() {
        return missionId;
    }

    public Long getSuperheroId() {
        return superheroId;
    }
}
