package exercise.gameongradle.rest;

import com.nonIt.GameOn.service.GameSubGenreService;
import com.nonIt.GameOn.service.createdto.GameSubGenreDto;
import com.nonIt.GameOn.service.restdto.GameSubGenreRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/{game-sub-SubGenres}")
//@PreAuthorize("hasRole('ADMIN')")
public class GameSubGenreResources {
    private final GameSubGenreService gameSubGenreService;

    @GetMapping
    public ResponseEntity<List<GameSubGenreRestDto>> getAllGameSubGenre() {
        return ResponseEntity.ok(gameSubGenreService.getAll());
    }

    @GetMapping(value = "/{gameSubGenreId}")
    public ResponseEntity<GameSubGenreRestDto> getGameSubGenreById(@PathVariable("gameSubGenreId") Integer gameSubGenreId) {
        return ResponseEntity.ok(gameSubGenreService.findById(gameSubGenreId));
    }

    @PostMapping
    public ResponseEntity<GameSubGenreRestDto> createGameSubGenre(@RequestBody GameSubGenreDto gameSubGenreDto) {
        return ResponseEntity.ok(gameSubGenreService.createGameSubGenre(gameSubGenreDto));
    }

    @PutMapping(value = "/{gameSubGenreId}")
    public ResponseEntity<GameSubGenreRestDto> updateGameSubGenreById(@PathVariable("gameSubGenreId") Integer gameSubGenreId, @RequestBody GameSubGenreDto gameSubGenreDto) {
        return ResponseEntity.ok(gameSubGenreService.updateGameSubGenre(gameSubGenreId, gameSubGenreDto));
    }

    @DeleteMapping(value = "/{gameSubGenreId}")
    public ResponseEntity<Void> deleteGameSubGenreById(@PathVariable("gameSubGenreId") Integer gameSubGenreId) {
        gameSubGenreService.deleteGameSubGenre(gameSubGenreId);
        return ResponseEntity.noContent().build();
    }
}
