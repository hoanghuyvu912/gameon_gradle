package exercise.gameongradle.service;

import exercise.gameongradle.rest.resourcesdto.SimplifiedGameDto;
import exercise.gameongradle.service.createdto.GameDto;
import exercise.gameongradle.service.customDto.GameLibraryDto;
import exercise.gameongradle.service.customDto.GameSearchDto;
import exercise.gameongradle.service.customDto.GameWithUsedGameCodeListDto;
import exercise.gameongradle.service.restdto.GameRestDto;

import java.time.LocalDate;
import java.util.List;

public interface GameService {
    //CRUD APIs
    List<SimplifiedGameDto> getAll();

    SimplifiedGameDto findById(Integer gameId);

    SimplifiedGameDto createGame(GameDto gameDto);

    GameRestDto updateGame(Integer gameId, GameDto gameDto);

    void deleteGame(Integer gameId);

    //Find featured games
    List<SimplifiedGameDto> getFeaturedGame();

    //Find recent best-seller games
    List<GameWithUsedGameCodeListDto> getRecentBestSellerGames();

    //Find recent worst-seller games
    List<GameWithUsedGameCodeListDto> getRecentWorstSellerGames();

    //Find by name
    List<GameRestDto> findByNameIgnoreCaseContaining(String name);


    //Find by foreign key
    List<GameRestDto> getByDeveloperId(Integer developerId);

    List<GameRestDto> getByPublisherId(Integer publisherId);

    List<GameLibraryDto> getByUser(String authorization);

    List<GameRestDto> getByGenreId(Integer genreId);

    List<GameRestDto> getByGenreName(String genreName);

    List<GameRestDto> getBySubGenreId(Integer subGenreId);

    List<GameRestDto> getBySubGenreName(String subGenreName);

    //Custom queries
    List<GameRestDto> getByRatingAndReleasedDateBetween(Integer rating1, Integer rating2, LocalDate date1, LocalDate date2);


    //    TEST ADVANCED SEARCH
    List<GameRestDto> getGamesByGameSearchDto(GameSearchDto gameSearchDto);


}
