package exercise.gameongradle.rest;

import com.nonIt.GameOn.service.UserRoleAssignmentService;
import com.nonIt.GameOn.service.createdto.UserRoleAssignmentDto;
import com.nonIt.GameOn.service.restdto.UserRoleAssignmentRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user-role-assignments")
//@PreAuthorize("hasRole('ADMIN')")
public class UserRoleAssignmentResources {
    private final UserRoleAssignmentService userRoleAssignmentService;

    @PostMapping
    public ResponseEntity<UserRoleAssignmentRestDto> createUserRoleAssignment(@RequestBody UserRoleAssignmentDto userRoleAssignmentDto) {
        return ResponseEntity.ok(userRoleAssignmentService.createUserRoleAssignment(userRoleAssignmentDto));
    }

    @PutMapping(value = "/{userRoleAssignmentId}")
    public ResponseEntity<UserRoleAssignmentRestDto> updateUserRoleAssignment(@RequestBody UserRoleAssignmentDto userRoleAssignmentDto, @PathVariable("userRoleAssignmentId") Integer userRoleAssignmentId) {
        return ResponseEntity.ok(userRoleAssignmentService.updateUserRoleAssignment(userRoleAssignmentDto, userRoleAssignmentId));
    }

}
