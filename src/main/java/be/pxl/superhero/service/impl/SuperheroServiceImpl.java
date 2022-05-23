package be.pxl.superhero.service.impl;

import be.pxl.superhero.api.Superhero.AddSuperheroToMissionRequest;
import be.pxl.superhero.api.Superhero.SuperheroDTO;
import be.pxl.superhero.api.Superhero.SuperheroRequest;
import be.pxl.superhero.domain.Mission;
import be.pxl.superhero.domain.Superhero;
import be.pxl.superhero.exception.ResourceNotFoundException;
import be.pxl.superhero.repository.MissionRepository;
import be.pxl.superhero.repository.SuperheroRepository;
import be.pxl.superhero.service.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuperheroServiceImpl implements SuperheroService {

    private final SuperheroRepository superheroRepository;
    private final MissionRepository missionRepository;

    @Autowired
    public SuperheroServiceImpl(SuperheroRepository superheroRepository, MissionRepository missionRepository) {
        this.superheroRepository = superheroRepository;
        this.missionRepository = missionRepository;
    }

    @Override
    public List<SuperheroDTO> findAllSuperheroes() {
        return superheroRepository.findAll()
                .stream().map(SuperheroDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public SuperheroDTO findSuperheroById(Long superheroId) {
        return superheroRepository.findById(superheroId)
                .map(SuperheroDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Superhero", "ID", superheroId));
    }

    @Override
    public SuperheroDTO createSuperhero(SuperheroRequest superheroRequest) {
        Superhero superhero = new Superhero();
        superhero.setFirstName(superheroRequest.getFirstName());
        superhero.setLastName(superheroRequest.getLastName());
        superhero.setSuperheroName(superheroRequest.getSuperheroName());
        Superhero newSuperhero = superheroRepository.save(superhero);
        return new SuperheroDTO(newSuperhero);
    }

    @Override
    public SuperheroDTO updateSuperhero(Long superheroId, SuperheroRequest superheroRequest) {
        return superheroRepository.findById(superheroId)
                .map(superhero -> {
                    superhero.setFirstName(superheroRequest.getFirstName());
                    superhero.setLastName(superheroRequest.getLastName());
                    superhero.setSuperheroName(superheroRequest.getSuperheroName());
                    return new SuperheroDTO(superheroRepository.save(superhero));
                }).orElseThrow(() -> new ResourceNotFoundException("Superhero", "id", superheroId));
    }

    @Override
    public boolean deleteSuperhero(Long superheroId) {
        return superheroRepository.findById(superheroId)
                .map(superhero -> {
                    superheroRepository.delete(superhero);
                    return true;
                }).orElseThrow(() -> new ResourceNotFoundException("Superhero", "ID", superheroId));
    }

    @Override
    @Transactional
    public SuperheroDTO addSuperheroToMission(AddSuperheroToMissionRequest addSuperheroToMissionRequest) {
        Optional<Mission> missionById = missionRepository.findById(addSuperheroToMissionRequest.getMissionId());
        if (missionById.isEmpty()) {
            throw new ResourceNotFoundException("Mission", "ID", addSuperheroToMissionRequest.getMissionId());
        }
        Optional<Superhero> superheroById = superheroRepository.findById(addSuperheroToMissionRequest.getSuperheroId());
        if (superheroById.isEmpty()) {
            throw new ResourceNotFoundException("Superhero", "ID", addSuperheroToMissionRequest.getSuperheroId());
        }

        superheroById.get().addMission(missionById.get());

        return new SuperheroDTO(superheroById.get());
    }
}
