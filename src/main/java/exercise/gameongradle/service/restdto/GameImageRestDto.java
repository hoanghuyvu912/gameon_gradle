package exercise.gameongradle.service.restdto;

import com.nonIt.GameOn.entity.Game;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameImageRestDto {
    private Integer id;

    private String imageLink;

    private Game game;
}
