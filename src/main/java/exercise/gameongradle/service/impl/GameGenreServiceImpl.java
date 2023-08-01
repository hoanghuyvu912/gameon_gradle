package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.Game;
import com.nonIt.GameOn.entity.GameGenre;
import com.nonIt.GameOn.entity.Genre;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.GameGenreRepository;
import com.nonIt.GameOn.repository.GameRepository;
import com.nonIt.GameOn.repository.GenreRepository;
import com.nonIt.GameOn.service.GameGenreService;
import com.nonIt.GameOn.service.createdto.GameGenreDto;
import com.nonIt.GameOn.service.mapper.GameGenreMapper;
import com.nonIt.GameOn.service.restdto.GameGenreRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GameGenreServiceImpl implements GameGenreService {
    private final GameGenreRepository gameGenreRepository;
    private final GenreRepository genreRepository;
    private final GameRepository gameRepository;
    private final GameGenreMapper gameGenreMapper;

    @Override
    public List<GameGenreRestDto> getAll() {
        return gameGenreRepository.findAll().stream().map(gameGenreMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public GameGenreRestDto findById(Integer gameGenreId) {
        return gameGenreRepository.findById(gameGenreId).map(gameGenreMapper::toDto).orElseThrow(GameOnException::GameGenreNotFound);
    }

    @Override
    public GameGenreRestDto createGameGenre(GameGenreDto gameGenreDto) {
        Game game = gameRepository.findById(gameGenreDto.getGameId()).orElseThrow(GameOnException::GameNotFound);
        Genre genre = genreRepository.findById(gameGenreDto.getGenreId()).orElseThrow(GameOnException::GenreNotFound);

        GameGenre gameGenre = GameGenre.builder()
                .game(game)
                .genre(genre)
                .build();

        gameGenre = gameGenreRepository.save(gameGenre);
        return gameGenreMapper.toDto(gameGenre);
    }

    @Override
    public GameGenreRestDto updateGameGenre(Integer gameGenreId, GameGenreDto gameGenreDto) {
        GameGenre gameGenre = gameGenreRepository.findById(gameGenreId).orElseThrow(GameOnException::GameGenreNotFound);

        if (gameGenreDto.getGameId() != null) {
            Game game = gameRepository.findById(gameGenreDto.getGameId()).orElseThrow(GameOnException::GameNotFound);
            gameGenre.setGame(game);
        }
        if (gameGenreDto.getGenreId() != null) {
            Genre genre = genreRepository.findById(gameGenreDto.getGenreId()).orElseThrow(GameOnException::GenreNotFound);
            gameGenre.setGenre(genre);
        }

        gameGenreRepository.save(gameGenre);

        return gameGenreMapper.toDto(gameGenre);
    }

    @Override
    public void deleteGameGenre(Integer gameGenreId) {
        gameGenreRepository.deleteById(gameGenreId);
    }
}
