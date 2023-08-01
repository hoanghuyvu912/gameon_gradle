package exercise.gameongradle.repository;

import com.nonIt.GameOn.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    List<Publisher> findByNameContaining(String name);

    List<Publisher> findByEstablishedDateAfter(LocalDate date);

    List<Publisher> findByEstablishedDateBefore(LocalDate date);
}
