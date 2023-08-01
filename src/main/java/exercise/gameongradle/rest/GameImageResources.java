package exercise.gameongradle.rest;

import com.nonIt.GameOn.service.GameImageService;
import com.nonIt.GameOn.service.createdto.GameImageDto;
import com.nonIt.GameOn.service.restdto.GameImageRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/game-images")
//@PreAuthorize("hasRole('ADMIN')")
public class GameImageResources {
    private final GameImageService gameImageService;

    @GetMapping
    public ResponseEntity<List<GameImageRestDto>> getAllGameImage() {
        return ResponseEntity.ok(gameImageService.getAll());
    }

    @GetMapping(value = "/{gameImageId}")
    public ResponseEntity<GameImageRestDto> getGameImageById(@PathVariable("gameImageId") Integer gameImageId) {
        return ResponseEntity.ok(gameImageService.findById(gameImageId));
    }

    @PostMapping
    public ResponseEntity<GameImageRestDto> createGameImage(@RequestBody GameImageDto gameImageDto) {
        return ResponseEntity.ok(gameImageService.createGameImage(gameImageDto));
    }

    @PutMapping(value = "/{gameImageId}")
    public ResponseEntity<GameImageRestDto> updateGameImage(@PathVariable("gameImageId") Integer gameImageId, @RequestBody GameImageDto gameImageDto) {
        return ResponseEntity.ok(gameImageService.updateGameImage(gameImageId, gameImageDto));
    }

    @DeleteMapping(value = "/{gameImageId}")
    public ResponseEntity<Void> deleteGameImageById(@PathVariable("gameImageId") Integer gameImageId) {
        gameImageService.deleteGameImage(gameImageId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/by-game-id/{gameId}")
    public ResponseEntity<List<GameImageRestDto>> getByGameId(@PathVariable("gameId") Integer gameId) {
        return ResponseEntity.ok(gameImageService.getByGameId(gameId));
    }

    @GetMapping(value = "/by-game-name")
    public ResponseEntity<List<GameImageRestDto>> getByGameName(@RequestParam("gameName") String gameName) {
        return ResponseEntity.ok(gameImageService.getByGameName(gameName));
    }
}
