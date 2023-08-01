package exercise.gameongradle.service.restdto;

import com.nonIt.GameOn.entity.Game;
import com.nonIt.GameOn.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRestDto {
    private Integer id;

    private User user;

    private Game game;

    private String commentContent;

    private LocalDate commentDate;
}
