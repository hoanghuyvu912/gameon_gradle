package exercise.gameongradle.service.restdto;

import com.nonIt.GameOn.entity.Game;
import com.nonIt.GameOn.entity.SubGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSubGenreRestDto {
    private Integer id;

    private Game game;

    private SubGenre subGenre;
}
