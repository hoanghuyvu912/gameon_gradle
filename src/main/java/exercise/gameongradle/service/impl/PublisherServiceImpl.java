package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.Publisher;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.PublisherRepository;
import com.nonIt.GameOn.service.PublisherService;
import com.nonIt.GameOn.service.createdto.PublisherDto;
import com.nonIt.GameOn.service.mapper.PublisherMapper;
import com.nonIt.GameOn.service.restdto.PublisherRestDto;
import exercise.gameongradle.entity.Publisher;
import exercise.gameongradle.exception.GameOnException;
import exercise.gameongradle.repository.PublisherRepository;
import exercise.gameongradle.service.PublisherService;
import exercise.gameongradle.service.createdto.PublisherDto;
import exercise.gameongradle.service.mapper.PublisherMapper;
import exercise.gameongradle.service.restdto.PublisherRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public List<PublisherRestDto> getAll() {
        return publisherRepository.findAll().stream().map(publisherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PublisherRestDto> findByNameContaining(String name) {
        if (name == null || name.trim().isBlank() || name.isEmpty()) {
            throw GameOnException.badRequest("NameMissing", "Query name is missing");
        }
        return publisherRepository.findByNameContaining(name).stream().map(publisherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PublisherRestDto> findByEstablishedDateAfter(LocalDate date) {
        if(date.isAfter(LocalDate.now())){
            throw GameOnException.badRequest("InvalidDate", "Query date cannot be after current date.");
        }
        return publisherRepository.findByEstablishedDateAfter(date).stream().map(publisherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PublisherRestDto> findByEstablishedDateBefore(LocalDate date) {
        return publisherRepository.findByEstablishedDateBefore(date).stream().map(publisherMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PublisherRestDto findById(Integer publisherId) {
        Publisher publisher = publisherRepository.findById(publisherId).orElseThrow(GameOnException::PublisherNotFound);
        return publisherMapper.toDto(publisher);
    }

    @Override
    public PublisherRestDto createPublisher(PublisherDto publisherDto) {
        if (publisherDto.getName() == null || publisherDto.getName().trim().isBlank() || publisherDto.getName().isEmpty()) {
            throw GameOnException.badRequest("DeveloperNameNotFound", "Developer's name is missing.");
        }
        if (publisherDto.getDescription() == null || publisherDto.getDescription().trim().isBlank() || publisherDto.getDescription().isEmpty()) {
            throw GameOnException.badRequest("DeveloperDescriptionNotFound", "Developer's sescription is missing.");
        }
        if (publisherDto.getThumbnail() == null || publisherDto.getThumbnail().trim().isBlank() || publisherDto.getThumbnail().isEmpty()) {
            throw GameOnException.badRequest("DeveloperThumbnailNotFound", "Developer's thumbnail is missing.");
        }
        if (publisherDto.getCoverPhoto() == null || publisherDto.getCoverPhoto().trim().isBlank() || publisherDto.getCoverPhoto().isEmpty()) {
            throw GameOnException.badRequest("DeveloperCoverPhotoNotFound", "Developer's cover photo is missing.");
        }
        if (publisherDto.getEstablishedDate().isAfter(LocalDate.now())) {
            throw GameOnException.badRequest("InvalidEstablishedDate", "Established date cannot be after current date.");
        }
        Publisher publisher = Publisher.builder()
                .name(publisherDto.getName())
                .description(publisherDto.getDescription())
                .thumbnail(publisherDto.getThumbnail())
                .coverPhoto(publisherDto.getCoverPhoto())
                .establishedDate(publisherDto.getEstablishedDate())
                .build();

        publisher = publisherRepository.save(publisher);
        return publisherMapper.toDto(publisher);
    }

    @Override
    public PublisherRestDto updatePublisher(Integer publisherId, PublisherDto publisherDto) {
        Publisher publisher = publisherRepository.findById(publisherId).orElseThrow(GameOnException::PublisherNotFound);

        if (publisherDto.getName() != null) {
            if (publisherDto.getName().trim().isBlank() || publisherDto.getName().isEmpty()) {
                throw GameOnException.badRequest("DeveloperNameNotFound", "Developer's name is missing.");
            }
        }
        if (publisherDto.getDescription() != null) {
            if (publisherDto.getDescription().trim().isBlank() || publisherDto.getDescription().isEmpty()) {
                throw GameOnException.badRequest("DeveloperDescriptionNotFound", "Developer's description is missing.");
            }
        }
        if (publisherDto.getThumbnail() != null) {
            if (publisherDto.getThumbnail().trim().isBlank() || publisherDto.getThumbnail().isEmpty()) {
                throw GameOnException.badRequest("DeveloperThumbnailNotFound", "Developer's thumbnail is missing.");
            }
        }
        if (publisherDto.getCoverPhoto() != null) {
            if (publisherDto.getCoverPhoto().trim().isBlank() || publisherDto.getCoverPhoto().isEmpty()) {
                throw GameOnException.badRequest("DeveloperCoverPhotoNotFound", "Developer's cover photo is missing.");
            }
        }
        if (publisherDto.getEstablishedDate() != null) {
            if (publisherDto.getEstablishedDate().isAfter(LocalDate.now())) {
                throw GameOnException.badRequest("InvalidEstablishedDate", "Established date cannot be after current date.");
            }
        }
        publisherMapper.mapFromDto(publisherDto, publisher);
        publisher = publisherRepository.save(publisher);
        return publisherMapper.toDto(publisher);
    }

    @Override
    public void deletePublisher(Integer PublisherId) {
        publisherRepository.deleteById(PublisherId);
    }
}
