package exercise.gameongradle.service.createdto;

import com.nonIt.GameOn.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleAssignmentDto {
    private LocalDateTime assignedDate;
    private Role role;
    private LocalDateTime updatedDate;
    private Integer userId;
}
