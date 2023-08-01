package exercise.gameongradle.security.service.impl;

import com.nonIt.GameOn.repository.UserRepository;
import com.nonIt.GameOn.security.service.UserSecurityService;
import com.nonIt.GameOn.security.service.dto.UserSecurityDto;
import com.nonIt.GameOn.security.service.mapper.UserSecurityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSecurityServiceImpl implements UserSecurityService {
    private final UserRepository userRepository;

    private final UserSecurityMapper userSecurityMapper;

    @Override
    public List<UserSecurityDto> getSecurityUsers() {
        return userSecurityMapper.mapToSecurityDtos(userRepository.findAll());
    }
}
