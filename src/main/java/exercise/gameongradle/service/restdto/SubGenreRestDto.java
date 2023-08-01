package exercise.gameongradle.service.restdto;

import com.nonIt.GameOn.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubGenreRestDto {
    private Integer id;

    private String name;

    private Genre genre;
}
