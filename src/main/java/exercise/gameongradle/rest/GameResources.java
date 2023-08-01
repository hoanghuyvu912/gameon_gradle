package exercise.gameongradle.rest;

import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.rest.resourcesdto.SimplifiedGameDto;
import com.nonIt.GameOn.service.GameService;
import com.nonIt.GameOn.service.createdto.GameDto;
import com.nonIt.GameOn.service.customDto.GameLibraryDto;
import com.nonIt.GameOn.service.customDto.GameSearchDto;
import com.nonIt.GameOn.service.customDto.GameWithUsedGameCodeListDto;
import com.nonIt.GameOn.service.restdto.GameRestDto;
import com.nonIt.GameOn.utils.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/games")
public class GameResources {
    private final GameService gameService;

    //CRUD APIs
    @GetMapping
    public ResponseEntity<List<SimplifiedGameDto>> getAllGame() {
        return ResponseEntity.ok(gameService.getAll());
    }
    
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SimplifiedGameDto> createGame(@Valid @RequestBody GameDto gameDto) {
        return ResponseEntity.ok(gameService.createGame(gameDto));
    }

    @GetMapping(value = "/{gameId}")
    public ResponseEntity<SimplifiedGameDto> getGameById(@PathVariable("gameId") Integer userId) {
        return ResponseEntity.ok(gameService.findById(userId));
    }

    @PutMapping(value = "/{gameId}")
    public ResponseEntity<GameRestDto> updateGameById(@PathVariable("gameId") Integer gameId, @RequestBody GameDto gameDto) {
        return ResponseEntity.ok(gameService.updateGame(gameId, gameDto));
    }

    @DeleteMapping(value = "/{gameId}")
    public ResponseEntity<Void> deleteGameById(@PathVariable("gameId") Integer gameId) {
        gameService.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }

    //Find by name
    @GetMapping(value = "/name-containing")
    public ResponseEntity<List<GameRestDto>> findByNameIgnoreCaseContaining(@RequestParam("name") String name) {
        return ResponseEntity.ok(gameService.findByNameIgnoreCaseContaining(name));
    }

    //Find featured games
    @GetMapping(value = "/featured")
    public ResponseEntity<List<SimplifiedGameDto>> getFeaturedGames() {
        return ResponseEntity.ok(gameService.getFeaturedGame());
    }

    @GetMapping(value = "/recent-best-seller")
    public ResponseEntity<List<GameWithUsedGameCodeListDto>> getRecentBestSellerGames() {
        return ResponseEntity.ok(gameService.getRecentBestSellerGames());
    }

    @GetMapping(value = "/recent-worst-seller")
    public ResponseEntity<List<GameWithUsedGameCodeListDto>> getRecentWorstSellerGames() {
        return ResponseEntity.ok(gameService.getRecentWorstSellerGames());
    }

    //Find by foreign key
    @GetMapping(value = "/by-developer-id")
    public ResponseEntity<List<GameRestDto>> getByDeveloperId(@RequestParam("developerId") Integer developerId) {
        return ResponseEntity.ok(gameService.getByDeveloperId(developerId));
    }

    @GetMapping(value = "/by-publisher-id")
    public ResponseEntity<List<GameRestDto>> getByPublisherId(@RequestParam("publisherId") Integer publisherId) {
        return ResponseEntity.ok(gameService.getByPublisherId(publisherId));
    }

    @GetMapping(value = "/library")
    public ResponseEntity<List<GameLibraryDto>> getGameLibrary(@RequestHeader("Authorization") String authorization) {
        return ResponseEntity.ok(gameService.getByUser(authorization.substring(7)));
    }

    @GetMapping(value = "/by-genre-id")
    public ResponseEntity<List<GameRestDto>> getByGenreId(@RequestParam("genreId") Integer genreId) {
        return ResponseEntity.ok(gameService.getByGenreId(genreId));
    }

    @GetMapping(value = "/by-genre-name")
    public ResponseEntity<List<GameRestDto>> getByGenreName(@RequestParam("genreName") String genreName) {
        return ResponseEntity.ok(gameService.getByGenreName("%" + genreName + "%"));
    }

    @GetMapping(value = "/by-sub-genre-id")
    public ResponseEntity<List<GameRestDto>> getBySubGenreId(@RequestParam("subGenreId") Integer subGenreId) {
        return ResponseEntity.ok(gameService.getBySubGenreId(subGenreId));
    }

    @GetMapping(value = "/by-sub-genre-name")
    public ResponseEntity<List<GameRestDto>> getBySubGenreName(@RequestParam("subGenreName") String subGenreName) {
        return ResponseEntity.ok(gameService.getBySubGenreName("%" + subGenreName + "%"));
    }

    @GetMapping("/rating-released-date-between")
    public ResponseEntity<List<GameRestDto>> getByRatingAndReleasedDateBetween(@RequestParam("rating1") Integer rating1, @RequestParam("rating2") Integer rating2, @RequestParam("date1") LocalDate date1, @RequestParam("date2") LocalDate date2) {
        return ResponseEntity.ok(gameService.getByRatingAndReleasedDateBetween(rating1, rating2, date1, date2));
    }

    @GetMapping("/search-by-dto")
    public ResponseEntity<List<GameRestDto>> searchByDto(@RequestBody GameSearchDto gameSearchDto) {
        boolean isGameSearchDtoNull = NullChecker.allNull(gameSearchDto);
        if (isGameSearchDtoNull) {
            throw GameOnException.badRequest("MissingSearchCriteria", "Search criteria not found.");
        }
        return ResponseEntity.ok(gameService.getGamesByGameSearchDto(gameSearchDto));
    }
}
