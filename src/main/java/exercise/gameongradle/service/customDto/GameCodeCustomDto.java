package exercise.gameongradle.service.customDto;

import com.nonIt.GameOn.entity.GameCodeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCodeCustomDto {
    private Integer gameCodeId;
    private Integer gameId;
    private GameCodeStatus gameCodeStatus;
}
