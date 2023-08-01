package exercise.gameongradle.service.customDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCodeResponseDto {
    private List<String> gameCodeList;
    private Integer gameId;
}
