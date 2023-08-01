package exercise.gameongradle.service.restdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreRestDto {
    private Integer id;
    private String name;
}
