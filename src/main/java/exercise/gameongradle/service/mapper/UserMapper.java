package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.User;
import exercise.gameongradle.service.createdto.UserDto;
import exercise.gameongradle.service.restdto.UserRestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserRestDto, User, UserDto>{
}
