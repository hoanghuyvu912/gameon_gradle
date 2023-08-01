package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.GameCode;
import exercise.gameongradle.service.createdto.GameCodeDto;
import exercise.gameongradle.service.customDto.GameCodeResponseDto;
import exercise.gameongradle.service.restdto.GameCodeRestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameCodeMapper extends EntityMapper<GameCodeRestDto, GameCode, GameCodeDto> {
    @Mapping(target = "gameId", source = "gameId")
    @Mapping(target = "gameCodeList", source = "gameCodeList")
    GameCodeResponseDto toGameCodeResponseDto(GameCodeDto gameCodeDto);
}

