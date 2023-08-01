package exercise.gameongradle.rest.resourcesdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimplifiedGameGenreDto {
    private Integer id;

    private Integer gameId;

    private String gameName;

    private Integer genreId;

    private String genreName;
}
