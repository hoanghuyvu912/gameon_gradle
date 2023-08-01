package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.User;
import com.nonIt.GameOn.entity.UserRoleAssignment;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.UserRepository;
import com.nonIt.GameOn.repository.UserRoleAssignmentRepository;
import com.nonIt.GameOn.service.UserRoleAssignmentService;
import com.nonIt.GameOn.service.createdto.UserRoleAssignmentDto;
import com.nonIt.GameOn.service.mapper.UserRoleAssignmentMapper;
import com.nonIt.GameOn.service.restdto.UserRoleAssignmentRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRoleAssignmentServiceImpl implements UserRoleAssignmentService {
    private final UserRepository userRepository;
    private final UserRoleAssignmentRepository userRoleAssignmentRepository;
    private final UserRoleAssignmentMapper userRoleAssignmentMapper;

    @Override
    public UserRoleAssignmentRestDto createUserRoleAssignment(UserRoleAssignmentDto userRoleAssignmentDto) {
        User user = userRepository.findById(userRoleAssignmentDto.getUserId()).orElseThrow(GameOnException::UserNotFound);

        UserRoleAssignment userRoleAssignment = UserRoleAssignment
                .builder()
                .role(userRoleAssignmentDto.getRole())
                .users(user)
                .assignedDate(userRoleAssignmentDto.getAssignedDate())
                .updatedDate(userRoleAssignmentDto.getUpdatedDate())
                .build();

        userRoleAssignment = userRoleAssignmentRepository.save(userRoleAssignment);

        return userRoleAssignmentMapper.toDto(userRoleAssignment);
    }


    @Override
    public UserRoleAssignmentRestDto updateUserRoleAssignment(UserRoleAssignmentDto userRoleAssignmentDto, Integer userRoleAssignmentId) {
        UserRoleAssignment userRoleAssignment = userRoleAssignmentRepository.findById(userRoleAssignmentId).orElseThrow(GameOnException::UserRoleAssignmentNotFound);
//        User user = userRepository.findById(userRoleAssignmentDto.getUserId()).orElseThrow(GameOnException::UserNotFound);

        userRoleAssignmentMapper.mapFromDto(userRoleAssignmentDto, userRoleAssignment);

        userRoleAssignment = userRoleAssignmentRepository.save(userRoleAssignment);

        return userRoleAssignmentMapper.toDto(userRoleAssignment);
    }
}
