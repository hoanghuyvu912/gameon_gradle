package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.SubGenre;
import exercise.gameongradle.service.createdto.SubGenreDto;
import exercise.gameongradle.service.restdto.SubGenreRestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubGenreMapper extends EntityMapper<SubGenreRestDto, SubGenre, SubGenreDto> {
}
