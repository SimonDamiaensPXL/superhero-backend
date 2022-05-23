package be.pxl.superhero.api.Mission;

import javax.validation.constraints.NotBlank;

public class UpdateMissionRequest {

    @NotBlank(message = "missionName must not be blank")
    private final String missionName;

    private final boolean completed;

    public UpdateMissionRequest(String missionName, boolean completed) {
        this.missionName = missionName;
        this.completed = completed;
    }

    public String getMissionName() {
        return missionName;
    }

    public boolean isCompleted() {
        return completed;
    }
}
