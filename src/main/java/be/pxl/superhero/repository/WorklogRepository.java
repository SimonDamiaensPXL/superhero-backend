package be.pxl.superhero.repository;

import be.pxl.superhero.domain.Worklog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorklogRepository extends JpaRepository<Worklog, Long> {
}
