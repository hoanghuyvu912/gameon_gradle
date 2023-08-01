package exercise.gameongradle.rest.resourcesdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplifiedGameSubGenreDto {
    private Integer id;

    private Integer gameId;

    private String gameName;

    private Integer subGenreId;

    private String subGenreName;
}
