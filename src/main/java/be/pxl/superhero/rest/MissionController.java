package be.pxl.superhero.rest;

import be.pxl.superhero.api.Mission.CreateMissionRequest;
import be.pxl.superhero.api.Mission.MissionDTO;
import be.pxl.superhero.api.Mission.UpdateMissionRequest;
import be.pxl.superhero.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
public class MissionController {

    private final MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping
    public List<MissionDTO> getAllMissions() {
        return missionService.findAllMissions();
    }

    @GetMapping("/{missionId}")
    public MissionDTO getMissionById(@PathVariable Long missionId) {
        return missionService.findMissionById(missionId);
    }

    @PostMapping
    public ResponseEntity<MissionDTO> createMission(@RequestBody CreateMissionRequest missionRequest)
    {
        return new ResponseEntity<>(missionService.createMission(missionRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{missionId}")
    public MissionDTO updateMission(@PathVariable Long missionId, @RequestBody UpdateMissionRequest missionRequest) {
        return missionService.updateMission(missionId, missionRequest);
    }

    @DeleteMapping("/{missionId}")
    public ResponseEntity<Void> deleteMission(@PathVariable Long missionId)
    {
        boolean deleted = missionService.deleteMission(missionId);
        return deleted? new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
