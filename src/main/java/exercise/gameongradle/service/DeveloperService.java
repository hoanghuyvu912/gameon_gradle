package exercise.gameongradle.service;

import exercise.gameongradle.service.createdto.DeveloperDto;
import exercise.gameongradle.service.restdto.DeveloperRestDto;

import java.time.LocalDate;
import java.util.List;

public interface DeveloperService {
    List<DeveloperRestDto> getAll();

    List<DeveloperRestDto> findByNameContaining(String name);

    List<DeveloperRestDto> findByEstablishedDateAfter(LocalDate date);

    List<DeveloperRestDto> findByEstablishedDateBefore(LocalDate date);

    DeveloperRestDto findById(Integer developerId);

    DeveloperRestDto createDeveloper(DeveloperDto developerDto);

    DeveloperRestDto updateDeveloper(Integer developerId, DeveloperDto developerDto);

    void deleteDeveloper(Integer developerId);

    List<DeveloperRestDto> getAllByAdmin(String authorization);
}
