package exercise.gameongradle.service.createdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
//    @NotNull
    private Integer userId;
//    @NotNull
    private Integer gameId;
    private String commentContent;
}
