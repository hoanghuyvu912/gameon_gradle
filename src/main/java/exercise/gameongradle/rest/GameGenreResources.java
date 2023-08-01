package exercise.gameongradle.rest;

import com.nonIt.GameOn.service.GameGenreService;
import com.nonIt.GameOn.service.createdto.GameGenreDto;
import com.nonIt.GameOn.service.restdto.GameGenreRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/game_genres")
//@PreAuthorize("hasRole('ADMIN')")
public class GameGenreResources {
    private final GameGenreService gameGenreService;

    @GetMapping
    public ResponseEntity<List<GameGenreRestDto>> getAllGameGenre() {
        return ResponseEntity.ok(gameGenreService.getAll());
    }

    @GetMapping(value = "/{gameGenreId}")
    public ResponseEntity<GameGenreRestDto> getGameGenreById(@PathVariable("gameGenreId") Integer gameGenreId) {
        return ResponseEntity.ok(gameGenreService.findById(gameGenreId));
    }

    @PostMapping
    public ResponseEntity<GameGenreRestDto> createGameGenre(@RequestBody GameGenreDto gameGenreDto) {
        return ResponseEntity.ok(gameGenreService.createGameGenre(gameGenreDto));
    }

    @PutMapping(value = "/{gameGenreId}")
    public ResponseEntity<GameGenreRestDto> updateGameGenreById(@PathVariable("gameGenreId") Integer gameGenreId, @RequestBody GameGenreDto gameGenreDto) {
        return ResponseEntity.ok(gameGenreService.updateGameGenre(gameGenreId, gameGenreDto));
    }

    @DeleteMapping(value = "/{gameGenreId}")
    public ResponseEntity<Void> deleteGameGenreById(@PathVariable("gameGenreId") Integer gameGenreId) {
        gameGenreService.deleteGameGenre(gameGenreId);
        return ResponseEntity.noContent().build();
    }
}
