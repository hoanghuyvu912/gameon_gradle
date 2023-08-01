package exercise.gameongradle.repository;

import com.nonIt.GameOn.entity.GameSubGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSubGenreRepository extends JpaRepository<GameSubGenre, Integer> {
}
