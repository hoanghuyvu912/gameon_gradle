package exercise.gameongradle.service.restdto;

import com.nonIt.GameOn.entity.Game;
import com.nonIt.GameOn.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameGenreRestDto {
    private Integer id;

    private Game game;

    private Genre genre;
}
