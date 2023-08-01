package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.Game;
import com.nonIt.GameOn.entity.GameSubGenre;
import com.nonIt.GameOn.entity.SubGenre;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.GameRepository;
import com.nonIt.GameOn.repository.GameSubGenreRepository;
import com.nonIt.GameOn.repository.SubGenreRepository;
import com.nonIt.GameOn.service.GameSubGenreService;
import com.nonIt.GameOn.service.createdto.GameSubGenreDto;
import com.nonIt.GameOn.service.mapper.GameSubGenreMapper;
import com.nonIt.GameOn.service.restdto.GameSubGenreRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GameSubGenreServiceImpl implements GameSubGenreService {
    private final GameSubGenreRepository GameSubGenreRepository;
    private final GameRepository gameRepository;
    private final SubGenreRepository subGenreRepository;
    private final GameSubGenreMapper GameSubGenreMapper;

    @Override
    public List<GameSubGenreRestDto> getAll() {
        return GameSubGenreRepository.findAll().stream().map(GameSubGenreMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public GameSubGenreRestDto findById(Integer gameSubGenreId) {
        return GameSubGenreRepository.findById(gameSubGenreId).map(GameSubGenreMapper::toDto).orElseThrow(GameOnException::GameSubGenreNotFound);
    }

    @Override
    public GameSubGenreRestDto createGameSubGenre(GameSubGenreDto gameSubGenreDto) {
        Game game = gameRepository.findById(gameSubGenreDto.getGameId()).orElseThrow(GameOnException::GameNotFound);
        SubGenre subGenre = subGenreRepository.findById(gameSubGenreDto.getSubGenreId()).orElseThrow(GameOnException::SubGenreNotFound);

        GameSubGenre gameSubGenre = GameSubGenre.builder()
                .game(game)
                .subGenre(subGenre)
                .build();

        gameSubGenre = GameSubGenreRepository.save(gameSubGenre);
        return GameSubGenreMapper.toDto(gameSubGenre);
    }

    @Override
    public GameSubGenreRestDto updateGameSubGenre(Integer gameSubGenreId, GameSubGenreDto gameSubGenreDto) {
        GameSubGenre gameSubGenre = GameSubGenreRepository.findById(gameSubGenreId).orElseThrow(GameOnException::GameSubGenreNotFound);

        Game game = gameRepository.findById(gameSubGenreDto.getGameId()).orElseThrow(GameOnException::GameNotFound);
        SubGenre subGenre = subGenreRepository.findById(gameSubGenreDto.getSubGenreId()).orElseThrow(GameOnException::SubGenreNotFound);

        gameSubGenre.setGame(game);
        gameSubGenre.setSubGenre(subGenre);

        GameSubGenreRepository.save(gameSubGenre);

        return GameSubGenreMapper.toDto(gameSubGenre);
    }

    @Override
    public void deleteGameSubGenre(Integer gameSubGenreId) {
        GameSubGenreRepository.deleteById(gameSubGenreId);
    }
}
