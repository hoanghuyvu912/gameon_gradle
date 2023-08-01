package exercise.gameongradle.service.createdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSubGenreDto {
    private Integer gameId;

    private Integer subGenreId;
}
