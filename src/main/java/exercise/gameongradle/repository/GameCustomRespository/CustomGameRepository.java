package exercise.gameongradle.repository.GameCustomRespository;

import exercise.gameongradle.entity.Game;
import exercise.gameongradle.service.customDto.GameSearchDto;

import java.util.List;

public interface CustomGameRepository {
    List<Game> findGamesBySystemReqAndPriceLessThan(String systemReq, Double price);

    List<Game> findGamesByDto(GameSearchDto gameSearchDto);
}
