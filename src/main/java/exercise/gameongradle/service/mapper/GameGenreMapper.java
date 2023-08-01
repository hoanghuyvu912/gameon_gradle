package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.GameGenre;
import exercise.gameongradle.rest.resourcesdto.SimplifiedGameGenreDto;
import exercise.gameongradle.service.createdto.GameGenreDto;
import exercise.gameongradle.service.restdto.GameGenreRestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameGenreMapper extends EntityMapper<GameGenreRestDto, GameGenre, GameGenreDto> {
    @Mapping(target = "gameId", source = "game.id")
    @Mapping(target = "gameName", source = "game.name")
    @Mapping(target = "genreId", source = "genre.id")
    @Mapping(target = "genreName", source = "genre.name")
    SimplifiedGameGenreDto toSimplifiedDto(GameGenre gameGenre);
}
