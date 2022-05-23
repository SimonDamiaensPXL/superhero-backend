package be.pxl.superhero.repository;

import be.pxl.superhero.domain.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperheroRepository extends JpaRepository<Superhero, Long> {
    Optional<Superhero> findSuperheroBySuperheroName(String superheroName);
}
