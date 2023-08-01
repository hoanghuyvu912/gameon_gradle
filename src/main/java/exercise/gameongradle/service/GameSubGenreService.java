package exercise.gameongradle.service;

import exercise.gameongradle.service.createdto.GameSubGenreDto;
import exercise.gameongradle.service.restdto.GameSubGenreRestDto;

import java.util.List;

public interface GameSubGenreService {
    List<GameSubGenreRestDto> getAll();

    GameSubGenreRestDto findById(Integer gameSubGenreId);
    GameSubGenreRestDto createGameSubGenre(GameSubGenreDto gameSubGenreDto);
    GameSubGenreRestDto updateGameSubGenre(Integer gameSubGenreId, GameSubGenreDto gameSubGenreDto);
    void deleteGameSubGenre(Integer gameSubGenreId);
}
