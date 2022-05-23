package be.pxl.superhero.service.impl;

import be.pxl.superhero.api.Worklog.WorklogDTO;
import be.pxl.superhero.api.Worklog.WorklogRequest;
import be.pxl.superhero.domain.Mission;
import be.pxl.superhero.domain.Superhero;
import be.pxl.superhero.domain.Worklog;
import be.pxl.superhero.exception.ResourceNotFoundException;
import be.pxl.superhero.repository.MissionRepository;
import be.pxl.superhero.repository.SuperheroRepository;
import be.pxl.superhero.repository.WorklogRepository;
import be.pxl.superhero.service.WorklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class WorklogServiceImpl implements WorklogService {
    private final SuperheroRepository superheroRepository;
    private final MissionRepository missionRepository;
    private final WorklogRepository worklogRepository;

    @Autowired
    public WorklogServiceImpl(SuperheroRepository superheroRepository, MissionRepository missionRepository, WorklogRepository worklogRepository) {
        this.superheroRepository = superheroRepository;
        this.missionRepository = missionRepository;
        this.worklogRepository = worklogRepository;
    }

    @Override
    @Transactional
    public WorklogDTO createWorklog(WorklogRequest worklogRequest) {
        Optional<Superhero> superheroByName = superheroRepository.findSuperheroBySuperheroName(worklogRequest.getSuperheroName());
        if (superheroByName.isEmpty()) {
            throw new ResourceNotFoundException("Superhero", "Name", worklogRequest.getSuperheroName());
        }

        Optional<Mission> missionById = missionRepository.findById(Long.parseLong(worklogRequest.getMission()));
        if (missionById.isEmpty()) {
            throw new ResourceNotFoundException("Mission", "Name", worklogRequest.getMission());
        }

        if (!missionById.get().getSuperheroes().contains(superheroByName.get()))
        {
            missionById.get().addSuperhero(superheroByName.get());
        }

        Worklog worklog = new Worklog();
        worklog.setSuperhero(superheroByName.get());
        worklog.setMission(missionById.get());
        worklog.setDate(LocalDate.parse(worklogRequest.getDate()));
        worklog.setTask(worklogRequest.getTask());

        worklog = worklogRepository.save(worklog);

        return new WorklogDTO(worklog);
    }
}
