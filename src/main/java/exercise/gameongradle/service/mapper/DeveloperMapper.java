package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.Developer;
import exercise.gameongradle.service.createdto.DeveloperDto;
import exercise.gameongradle.service.restdto.DeveloperRestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeveloperMapper extends EntityMapper<DeveloperRestDto, Developer, DeveloperDto> {
}
