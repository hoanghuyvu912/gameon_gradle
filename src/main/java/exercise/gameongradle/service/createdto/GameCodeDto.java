package exercise.gameongradle.service.createdto;

import com.nonIt.GameOn.entity.GameCodeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCodeDto {
    private List<String> gameCodeList;
    private Integer gameId;
    private GameCodeStatus gameCodeStatus;
    private String gameCode;
}
