package exercise.gameongradle.service.restdto;

import com.nonIt.GameOn.entity.Game;
import com.nonIt.GameOn.entity.GameCodeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCodeRestDto {
    private Integer id;
    private List<String> gameCodeList;
    private Game game;
    private GameCodeStatus gameCodeStatus;
}
