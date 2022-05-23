package be.pxl.superhero.service;

import be.pxl.superhero.api.Mission.CreateMissionRequest;
import be.pxl.superhero.api.Mission.MissionDTO;
import be.pxl.superhero.api.Mission.MissionDetailDTO;
import be.pxl.superhero.api.Mission.UpdateMissionRequest;

import java.util.List;

public interface MissionService {
    List<MissionDTO> findAllMissions();

    List<MissionDTO> findAllUncompletedMissions();

    MissionDetailDTO findMissionById(Long missionId);

    MissionDTO createMission(CreateMissionRequest missionRequest);

    MissionDetailDTO updateMission(Long missionId, UpdateMissionRequest missionRequest);

    boolean deleteMission(Long missionId);
}
