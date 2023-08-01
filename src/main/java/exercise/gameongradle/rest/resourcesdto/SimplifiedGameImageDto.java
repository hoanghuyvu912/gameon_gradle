package exercise.gameongradle.rest.resourcesdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SimplifiedGameImageDto {
    private Integer id;
    private Integer gameId;
    private String imageLink;
}
