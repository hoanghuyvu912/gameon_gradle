package exercise.gameongradle.service.customDto;

import com.nonIt.GameOn.rest.resourcesdto.SimplifiedGameDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameWithUsedGameCodeListDto {
    private SimplifiedGameDto simplifiedGameDto;
//    private Game game;
    private Integer numberOfUsedGameCode;
}
