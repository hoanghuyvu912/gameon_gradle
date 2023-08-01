package exercise.gameongradle.service.customDto;

import com.nonIt.GameOn.entity.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameStatisticsDto {
    private Game game;
    private Long gameCodeList;
    private Double revenue;
}
