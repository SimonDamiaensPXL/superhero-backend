package be.pxl.superhero.service.impl;

import be.pxl.superhero.api.Mission.CreateMissionRequest;
import be.pxl.superhero.api.Mission.MissionDTO;
import be.pxl.superhero.api.Mission.MissionDetailDTO;
import be.pxl.superhero.api.Mission.UpdateMissionRequest;
import be.pxl.superhero.domain.Mission;
import be.pxl.superhero.exception.ResourceNotFoundException;
import be.pxl.superhero.repository.MissionRepository;
import be.pxl.superhero.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    @Autowired
    public MissionServiceImpl(MissionRepository missionRepository)
    {
        this.missionRepository = missionRepository;
    }

    @Override
    public List<MissionDTO> findAllMissions() {
        return missionRepository.findAll()
                .stream().map(MissionDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<MissionDTO> findAllUncompletedMissions()
    {
        return missionRepository.findAll()
                .stream()
                .filter(m -> !m.isCompleted())
                .filter(m -> !m.isDeleted())
                .map(MissionDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public MissionDetailDTO findMissionById(Long missionId) {
        return missionRepository.findById(missionId)
                .map(MissionDetailDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Mission", "ID", missionId));
    }

    @Override
    public MissionDTO createMission(CreateMissionRequest missionRequest) {
        Optional<Mission> missionByName = missionRepository.findMissionByName(missionRequest.getMissionName());
        if (missionByName.isPresent()) {
            throw new ValidationException("Name already exists");
        }
        Mission mission = new Mission();
        mission.setName(missionRequest.getMissionName());
        Mission savedMission = missionRepository.save(mission);
        return new MissionDTO(savedMission);
    }

    @Override
    public MissionDetailDTO updateMission(Long missionId, UpdateMissionRequest missionRequest) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new ResourceNotFoundException("Mission", "ID", missionId));
        mission.setCompleted(missionRequest.isCompleted());
        return new MissionDetailDTO(mission);
    }

    @Override
    public boolean deleteMission(Long missionId) {
        return missionRepository.findById(missionId)
                .map(superhero -> {
                    missionRepository.delete(superhero);
                    return true;
                }).orElseThrow(() -> new ResourceNotFoundException("Mission", "ID", missionId));
    }
}
