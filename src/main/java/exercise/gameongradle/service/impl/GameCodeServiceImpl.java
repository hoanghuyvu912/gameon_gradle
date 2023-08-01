package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.Game;
import com.nonIt.GameOn.entity.GameCode;
import com.nonIt.GameOn.entity.GameCodeStatus;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.GameCodeRepository;
import com.nonIt.GameOn.repository.GameRepository;
import com.nonIt.GameOn.service.GameCodeService;
import com.nonIt.GameOn.service.createdto.GameCodeDto;
import com.nonIt.GameOn.service.customDto.GameCodeResponseDto;
import com.nonIt.GameOn.service.mapper.GameCodeMapper;
import com.nonIt.GameOn.service.restdto.GameCodeRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GameCodeServiceImpl implements GameCodeService {
    private final GameCodeRepository gameCodeRepository;
    private final GameCodeMapper gameCodeMapper;
    private final GameRepository gameRepository;

    @Override
    public List<GameCodeRestDto> getAll() {
        return gameCodeRepository.findAll().stream().map(gameCodeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public GameCodeResponseDto createListGameCodeForGame(GameCodeDto gameCodeDto) {
        Game game = gameRepository.findById(gameCodeDto.getGameId()).orElseThrow(GameOnException::GameNotFound);

        Set<String> uniqueCodes = new HashSet<>();

        gameCodeDto.getGameCodeList().forEach(gameCode -> {
            if (uniqueCodes.contains(gameCode)) {
                throw GameOnException.badRequest("DuplicateGameCodeFound" ,"Duplicate Game Code Found");
            }
            uniqueCodes.add(gameCode);
            GameCode newGameCode = GameCode.builder()
                    .gameCodeStatus(GameCodeStatus.Available)
                    .gameCode(gameCode)
                    .game(game)
                    .build();
            game.getGameCodeList().add(newGameCode);
            gameCodeRepository.save(newGameCode);
        });

        return gameCodeMapper.toGameCodeResponseDto(gameCodeDto);
    }

    @Override
    public GameCodeRestDto updateGameCode(Integer gameCodeId, GameCodeDto gameCodeDto) {
        GameCode gameCode = gameCodeRepository.findById(gameCodeId).orElseThrow(GameOnException::GameCodeNotFound);

        if (gameCodeDto.getGameId() != null) {
            Game game = gameRepository.findById(gameCodeDto.getGameId()).orElseThrow(GameOnException::GameNotFound);
        }

        if (gameCodeDto.getGameCode() != null) {
            if (gameCodeDto.getGameCode().trim().isBlank() || gameCodeDto.getGameCode().trim().isEmpty()) {
                throw GameOnException.badRequest("GameCodeNotFound", "Game Code is missing");
            }
        }

        if (gameCodeDto.getGameCodeStatus() != null) {
            if (!gameCodeDto.getGameCodeStatus().equals(GameCodeStatus.Available) && !gameCodeDto.getGameCodeStatus().equals(GameCodeStatus.Used)) {
                throw GameOnException.badRequest("InvalidGameCodeStatus", "Game code status must be AVAILABLE or USED");
            }
        }

        gameCodeMapper.mapFromDto(gameCodeDto, gameCode);
        gameCodeRepository.save(gameCode);
        return gameCodeMapper.toDto(gameCode);
    }

    @Override
    public void deleteGameCode(Integer gameCodeId) {
        GameCode gameCode = gameCodeRepository.findById(gameCodeId).orElseThrow(GameOnException::GameCodeNotFound);

        gameCodeRepository.deleteById(gameCodeId);
    }


}

