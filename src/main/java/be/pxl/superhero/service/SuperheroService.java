package be.pxl.superhero.service;

import be.pxl.superhero.api.Superhero.AddSuperheroToMissionRequest;
import be.pxl.superhero.api.Superhero.SuperheroRequest;
import be.pxl.superhero.api.Superhero.SuperheroDTO;

import java.util.List;

public interface SuperheroService {

    List<SuperheroDTO> findAllSuperheroes();

    SuperheroDTO findSuperheroById(Long superheroId);

    SuperheroDTO createSuperhero(SuperheroRequest superheroRequest);

    SuperheroDTO updateSuperhero(Long superheroId, SuperheroRequest superheroRequest);

    boolean deleteSuperhero(Long superheroId);

    SuperheroDTO addSuperheroToMission(AddSuperheroToMissionRequest addSuperheroToMissionRequest);

}
