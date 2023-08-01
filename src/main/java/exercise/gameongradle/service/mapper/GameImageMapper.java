package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.GameImage;
import exercise.gameongradle.rest.resourcesdto.SimplifiedGameImageDto;
import exercise.gameongradle.service.createdto.GameImageDto;
import exercise.gameongradle.service.restdto.GameImageRestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameImageMapper extends EntityMapper<GameImageRestDto, GameImage, GameImageDto> {
    @Mapping(target = "gameId", source = "game.id")
    SimplifiedGameImageDto toSimplifiedDto(GameImage gameImage);
}
