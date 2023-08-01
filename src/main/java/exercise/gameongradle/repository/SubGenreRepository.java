package exercise.gameongradle.repository;

import com.nonIt.GameOn.entity.SubGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubGenreRepository extends JpaRepository<SubGenre, Integer> {
}
