package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.UserRoleAssignment;
import exercise.gameongradle.service.createdto.UserRoleAssignmentDto;
import exercise.gameongradle.service.restdto.UserRoleAssignmentRestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleAssignmentMapper extends EntityMapper<UserRoleAssignmentRestDto, UserRoleAssignment, UserRoleAssignmentDto> {
}
