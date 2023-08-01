package exercise.gameongradle.repository;

import exercise.gameongradle.entity.GameImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameImageRepository extends JpaRepository<GameImage, Integer> {
    @Query(value = "SELECT gi from GameImage gi join Game g on gi.game.id = g.id where g.id = :gameId")
    List<GameImage> getByGameId(@Param("gameId") Integer gameId);

    @Query(value = "SELECT gi from GameImage gi join Game g on gi.game.id = g.id where g.name like :gameName")
    List<GameImage> getByGameName(@Param("gameName") String gameName);
}
