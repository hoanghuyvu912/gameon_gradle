package exercise.gameongradle.security.service;

import com.nonIt.GameOn.security.service.dto.UserSecurityDto;

import java.util.List;

public interface UserSecurityService {
    List<UserSecurityDto> getSecurityUsers();
}
