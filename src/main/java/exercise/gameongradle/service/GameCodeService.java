package exercise.gameongradle.service;

import exercise.gameongradle.service.createdto.GameCodeDto;
import exercise.gameongradle.service.customDto.GameCodeResponseDto;
import exercise.gameongradle.service.restdto.GameCodeRestDto;

import java.util.List;

public interface GameCodeService {
    List<GameCodeRestDto> getAll();
    GameCodeResponseDto createListGameCodeForGame(GameCodeDto gameCodeDto);
    GameCodeRestDto updateGameCode(Integer gameCodeId, GameCodeDto gameCodeDto);

    void deleteGameCode(Integer gameCodeId);
}
