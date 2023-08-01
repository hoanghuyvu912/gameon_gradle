package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.Developer;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.DeveloperRepository;
import com.nonIt.GameOn.repository.UserRepository;
import com.nonIt.GameOn.security.jwt.JwtUtils;
import com.nonIt.GameOn.service.DeveloperService;
import com.nonIt.GameOn.service.createdto.DeveloperDto;
import com.nonIt.GameOn.service.mapper.DeveloperMapper;
import com.nonIt.GameOn.service.restdto.DeveloperRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    @Autowired
    private final JwtUtils jwtUtils;
    private final DeveloperRepository developerRepository;
    private final DeveloperMapper developerMapper;
    private final UserRepository userRepository;

    @Override
    public List<DeveloperRestDto> getAll() {
        return developerRepository.findAll().stream().map(developerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DeveloperRestDto> findByNameContaining(String name) {
        if (name == null || name.trim().isBlank() || name.isEmpty()) {
            throw GameOnException.badRequest("NameMissing", "Query name is missing.");
        }
        return developerRepository.findByNameContaining(name).stream().map(developerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DeveloperRestDto> findByEstablishedDateAfter(LocalDate date) {
        if(date.isAfter(LocalDate.now())){
            throw GameOnException.badRequest("InvalidDate", "Query date cannot be after current date.");
        }
        return developerRepository.findByEstablishedDateAfter(date).stream().map(developerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DeveloperRestDto> findByEstablishedDateBefore(LocalDate date) {
        return developerRepository.findByEstablishedDateBefore(date).stream().map(developerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public DeveloperRestDto findById(Integer developerId) {
        Developer developer = developerRepository.findById(developerId).orElseThrow(GameOnException::DeveloperNotFound);
        return developerMapper.toDto(developer);
    }

    @Override
    public DeveloperRestDto createDeveloper(DeveloperDto developerDto) {
        if (developerDto.getName() == null || developerDto.getName().trim().isBlank() || developerDto.getName().isEmpty()) {
            throw GameOnException.badRequest("DeveloperNameNotFound", "Developer's name is missing.");
        }
        if (developerDto.getDescription() == null || developerDto.getDescription().trim().isBlank() || developerDto.getDescription().isEmpty()) {
            throw GameOnException.badRequest("DeveloperDescriptionNotFound", "Developer's sescription is missing.");
        }
        if (developerDto.getThumbnail() == null || developerDto.getThumbnail().trim().isBlank() || developerDto.getThumbnail().isEmpty()) {
            throw GameOnException.badRequest("DeveloperThumbnailNotFound", "Developer's thumbnail is missing.");
        }
        if (developerDto.getCoverPhoto() == null || developerDto.getCoverPhoto().trim().isBlank() || developerDto.getCoverPhoto().isEmpty()) {
            throw GameOnException.badRequest("DeveloperCoverPhotoNotFound", "Developer's cover photo is missing.");
        }
        if (developerDto.getEstablishedDate().isAfter(LocalDate.now())) {
            throw GameOnException.badRequest("InvalidEstablishedDate", "Established date cannot be after current date.");
        }

        Developer developer = Developer.builder()
                .name(developerDto.getName())
                .description(developerDto.getDescription())
                .thumbnail(developerDto.getThumbnail())
                .coverPhoto(developerDto.getCoverPhoto())
                .establishedDate(developerDto.getEstablishedDate())
                .build();

        developer = developerRepository.save(developer);
        return developerMapper.toDto(developer);
    }

    @Override
    public DeveloperRestDto updateDeveloper(Integer developerId, DeveloperDto developerDto) {
        Developer developer = developerRepository.findById(developerId).orElseThrow(GameOnException::DeveloperNotFound);

        if (developerDto.getName() != null) {
            if (developerDto.getName().trim().isBlank() || developerDto.getName().isEmpty()) {
                throw GameOnException.badRequest("DeveloperNameNotFound", "Developer's name is missing.");
            }
        }
        if (developerDto.getDescription() != null) {
            if (developerDto.getDescription().trim().isBlank() || developerDto.getDescription().isEmpty()) {
                throw GameOnException.badRequest("DeveloperDescriptionNotFound", "Developer's sescription is missing.");
            }
        }
        if (developerDto.getThumbnail() != null) {
            if (developerDto.getThumbnail().trim().isBlank() || developerDto.getThumbnail().isEmpty()) {
                throw GameOnException.badRequest("DeveloperThumbnailNotFound", "Developer's thumbnail is missing.");
            }
        }
        if (developerDto.getCoverPhoto() != null) {
            if (developerDto.getCoverPhoto().trim().isBlank() || developerDto.getCoverPhoto().isEmpty()) {
                throw GameOnException.badRequest("DeveloperCoverPhotoNotFound", "Developer's cover photo is missing.");
            }
        }
        if (developerDto.getEstablishedDate() != null) {
            if (developerDto.getEstablishedDate().isAfter(LocalDate.now())) {
                throw GameOnException.badRequest("InvalidEstablishedDate", "Established date cannot be after current date.");
            }
        }
        developerMapper.mapFromDto(developerDto, developer);
        developer = developerRepository.save(developer);
        return developerMapper.toDto(developer);
    }

    @Override
    public void deleteDeveloper(Integer developerId) {
        developerRepository.deleteById(developerId);
    }

    @Override
    public List<DeveloperRestDto> getAllByAdmin(String authorization) {

        return null;
    }
}
