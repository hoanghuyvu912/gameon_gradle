package exercise.gameongradle.rest.resourcesdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedCommentDto {
    private Integer id;
    private Integer userId;
    private String username;
    private Integer gameId;
    private String commentContent;
    private LocalDate commentDate;
}
