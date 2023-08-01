package exercise.gameongradle.utils;

import exercise.gameongradle.entity.Game;
import exercise.gameongradle.rest.resourcesdto.*;
import exercise.gameongradle.service.mapper.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConvertToSimplifiedDto {
    private final GameMapper gameMapper;
    private final RatingMapper ratingMapper;
    private final CommentMapper commentMapper;
    private final GameImageMapper gameImageMapper;
    private final GameSubGenreMapper gameSubGenreMapper;
    private final GameGenreMapper gameGenreMapper;

    public SimplifiedGameDto convertGameEntityToSimplifiedDto(Game game) {

        SimplifiedGameDto simplifiedGameDto = gameMapper.toSimplifiedDto(game);
        List<SimplifiedCommentDto> simplifiedCommentDtoList = game.getCommentList().stream()
                .map(commentMapper::toSimplifiedDto)
                .collect(Collectors.toList());

        List<SimplifiedRatingDto> simplifiedRatingDtoList = game.getRatingList().stream()
                .map(ratingMapper::toSimplifiedDto)
                .collect(Collectors.toList());

        List<SimplifiedGameImageDto> simplifiedGameImageDtoList = game.getGameImageList().stream()
                .map(gameImageMapper::toSimplifiedDto)
                .collect(Collectors.toList());

        List<SimplifiedGameGenreDto> simplifiedGameGenreDtoList = game.getGameGenreList().stream()
                .map(gameGenreMapper::toSimplifiedDto)
                .collect(Collectors.toList());

        List<SimplifiedGameSubGenreDto> simplifiedGameSubGenreDtoList = game.getGameSubGenreList().stream()
                .map(gameSubGenreMapper::toSimplifiedDto)
                .collect(Collectors.toList());


        simplifiedGameDto.setSimplifiedCommentDtoList(simplifiedCommentDtoList);
        simplifiedGameDto.setSimplifiedRatingDtoList(simplifiedRatingDtoList);
        simplifiedGameDto.setSimplifiedGameGenreDtoList(simplifiedGameGenreDtoList);
        simplifiedGameDto.setSimplifiedGameSubGenreDtoList(simplifiedGameSubGenreDtoList);
        simplifiedGameDto.setSimplifiedGameImageDtoList(simplifiedGameImageDtoList);
        return simplifiedGameDto;
    }
}
