package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.Game;
import exercise.gameongradle.rest.resourcesdto.SimplifiedGameDto;
import exercise.gameongradle.service.createdto.GameDto;
import exercise.gameongradle.service.customDto.GameWithRatingDto;
import exercise.gameongradle.service.restdto.GameRestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper extends EntityMapper<GameRestDto, Game, GameDto> {
    GameWithRatingDto toGameWithRatingDto(Game game);

    @Mapping(target = "developerId", source = "developer.id")
    @Mapping(target = "publisherId", source = "publisher.id")
    @Mapping(target = "developerName", source = "developer.name")
    @Mapping(target = "publisherName", source = "publisher.name")
    SimplifiedGameDto toSimplifiedDto(Game game);
}
