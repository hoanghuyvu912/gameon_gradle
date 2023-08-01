package exercise.gameongradle.repository;

import exercise.gameongradle.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
    List<Developer> findByNameContaining(String name);

    List<Developer> findByEstablishedDateAfter(LocalDate date);

    List<Developer> findByEstablishedDateBefore(LocalDate date);
}
