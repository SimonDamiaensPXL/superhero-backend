package be.pxl.superhero.builder;

import be.pxl.superhero.api.Mission.MissionDTO;

public final class MissionDTOBuilder {
	public static final Long ID = 17L;
	public static final String MISSION_NAME = "Secret Mission";
	private Long id = ID;
	private String missionName = MISSION_NAME;
	private boolean completed;
	private boolean deleted;

	private MissionDTOBuilder() {}

	public static MissionDTOBuilder aMissionDTO() {return new MissionDTOBuilder();}

	public MissionDTOBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public MissionDTOBuilder withMissionName(String missionName) {
		this.missionName = missionName;
		return this;
	}

	public MissionDTOBuilder withCompleted(boolean completed) {
		this.completed = completed;
		return this;
	}

	public MissionDTOBuilder withDeleted(boolean deleted) {
		this.deleted = deleted;
		return this;
	}

	public MissionDTO build() {
		MissionDTO missionDTO = new MissionDTO();
		missionDTO.setId(id);
		missionDTO.setName(missionName);
		missionDTO.setCompleted(completed);
		missionDTO.setDeleted(deleted);
		return missionDTO;
	}
}
