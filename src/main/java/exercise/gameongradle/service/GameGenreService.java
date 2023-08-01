package exercise.gameongradle.service;

import exercise.gameongradle.service.createdto.GameGenreDto;
import exercise.gameongradle.service.restdto.GameGenreRestDto;

import java.util.List;

public interface GameGenreService {
    List<GameGenreRestDto> getAll();

    GameGenreRestDto findById(Integer gameGenreId);
    GameGenreRestDto createGameGenre(GameGenreDto gameGenreDto);
    GameGenreRestDto updateGameGenre(Integer gameGenreId, GameGenreDto gameGenreDto);
    void deleteGameGenre(Integer gameGenreId);
}
