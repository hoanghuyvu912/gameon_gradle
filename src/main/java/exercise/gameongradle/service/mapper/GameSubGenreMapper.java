package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.GameSubGenre;
import exercise.gameongradle.rest.resourcesdto.SimplifiedGameSubGenreDto;
import exercise.gameongradle.service.createdto.GameSubGenreDto;
import exercise.gameongradle.service.restdto.GameSubGenreRestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameSubGenreMapper extends EntityMapper<GameSubGenreRestDto, GameSubGenre, GameSubGenreDto> {
    @Mapping(target = "gameId", source = "game.id")
    @Mapping(target = "gameName", source = "game.name")
    @Mapping(target = "subGenreId", source = "subGenre.id")
    @Mapping(target = "subGenreName", source = "subGenre.name")
    SimplifiedGameSubGenreDto toSimplifiedDto(GameSubGenre gameSubGenre);
}
