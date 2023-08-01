package exercise.gameongradle.service;

import exercise.gameongradle.service.createdto.UserRoleAssignmentDto;
import exercise.gameongradle.service.restdto.UserRoleAssignmentRestDto;

public interface UserRoleAssignmentService {
    UserRoleAssignmentRestDto createUserRoleAssignment(UserRoleAssignmentDto userRoleAssignmentDto);

    UserRoleAssignmentRestDto updateUserRoleAssignment(UserRoleAssignmentDto userRoleAssignmentDto, Integer userRoleAssignmentId);
}
