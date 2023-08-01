package exercise.gameongradle.security.service.mapper;

import com.nonIt.GameOn.entity.User;
import com.nonIt.GameOn.security.service.dto.UserSecurityDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserSecurityMapper {
    UserSecurityDto mapToSecurityDto(User user);

    List<UserSecurityDto> mapToSecurityDtos(List<User> users);
}
