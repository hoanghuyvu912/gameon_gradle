package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.Genre;
import exercise.gameongradle.service.createdto.GenreDto;
import exercise.gameongradle.service.restdto.GenreRestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreMapper extends EntityMapper<GenreRestDto, Genre, GenreDto>{
}
