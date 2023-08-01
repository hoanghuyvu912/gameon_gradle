package exercise.gameongradle.service;

import exercise.gameongradle.rest.resourcesdto.SimplifiedRatingDto;
import exercise.gameongradle.service.createdto.RatingDto;
import exercise.gameongradle.service.restdto.RatingRestDto;

import java.util.List;

public interface RatingService {
    List<RatingRestDto> getAll();

    RatingRestDto findById(Integer ratingId);

    SimplifiedRatingDto createRating(RatingDto ratingDto);

    RatingRestDto updateRating(Integer ratingId, RatingDto ratingDto);

    void deleteRating(Integer ratingId);

    List<RatingRestDto> getByUserId(Integer userId);
//    List<Rating> demo();

//    List<GameRestDto> getGameByRatingBetweenAndReleasedDateBetween(Integer rating1, Integer rating2, LocalDate date1, LocalDate date2);
}
