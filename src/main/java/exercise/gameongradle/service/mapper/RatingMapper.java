package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.Rating;
import exercise.gameongradle.rest.resourcesdto.SimplifiedRatingDto;
import exercise.gameongradle.service.createdto.RatingDto;
import exercise.gameongradle.service.restdto.RatingRestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RatingMapper extends EntityMapper<RatingRestDto, Rating, RatingDto> {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "gameName", source = "game.name")
    SimplifiedRatingDto toSimplifiedDto(Rating rating);
}
