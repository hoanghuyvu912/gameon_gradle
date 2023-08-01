package exercise.gameongradle.rest;

import com.nonIt.GameOn.service.GameCodeService;
import com.nonIt.GameOn.service.createdto.GameCodeDto;
import com.nonIt.GameOn.service.customDto.GameCodeResponseDto;
import com.nonIt.GameOn.service.restdto.GameCodeRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/gamecodes")
public class GameCodeResources {
    private final GameCodeService gameCodeService;

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<GameCodeRestDto>> getAllGameCodes() {
        return ResponseEntity.ok(gameCodeService.getAll());
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<GameCodeResponseDto> createGameCodeForGame(@RequestBody GameCodeDto gameCodeDto) {
        return ResponseEntity.ok(gameCodeService.createListGameCodeForGame(gameCodeDto));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{gameCodeId}")
    public ResponseEntity<GameCodeRestDto> updateGameCode(@PathVariable("gameCodeId") Integer gameCodeId ,@RequestBody GameCodeDto gameCodeDto){
        return ResponseEntity.ok(gameCodeService.updateGameCode(gameCodeId,gameCodeDto));
    }

    @DeleteMapping("/{gameCodeId}")
    public ResponseEntity<Void> deleteGameCode(@PathVariable("gameCodeId") Integer gameCodeId){
        gameCodeService.deleteGameCode(gameCodeId);
        return ResponseEntity.noContent().build();
    }
}
