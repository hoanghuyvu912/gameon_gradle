package exercise.gameongradle.service.restdto;

import com.nonIt.GameOn.entity.Gender;
import com.nonIt.GameOn.entity.UserRoleAssignment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRestDto {
    private Integer id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private String tel;

    private String address;

    private LocalDate dob;

    private Gender gender;

    private String profileImg;

    private Double balance;

    private LocalDate registeredDate;

    //    private Role role;
    private List<UserRoleAssignment> roles = new ArrayList<>();

    private boolean active;
}
