package exercise.gameongradle.service;

import exercise.gameongradle.service.createdto.GameImageDto;
import exercise.gameongradle.service.restdto.GameImageRestDto;

import java.util.List;

public interface GameImageService {
    List<GameImageRestDto> getAll();

    GameImageRestDto findById(Integer gameImageId);

    GameImageRestDto createGameImage(GameImageDto gameImageDto);

    GameImageRestDto updateGameImage(Integer gameImageId, GameImageDto gameImageDto);

    void deleteGameImage(Integer gameImageId);

    List<GameImageRestDto> getByGameId(Integer gameId);

    List<GameImageRestDto> getByGameName(String gameName);
}
