package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.Game;
import com.nonIt.GameOn.entity.GameImage;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.GameImageRepository;
import com.nonIt.GameOn.repository.GameRepository;
import com.nonIt.GameOn.service.GameImageService;
import com.nonIt.GameOn.service.createdto.GameImageDto;
import com.nonIt.GameOn.service.mapper.GameImageMapper;
import com.nonIt.GameOn.service.restdto.GameImageRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GameImageServiceImpl implements GameImageService {
    private final GameImageRepository gameImageRepository;
    private final GameRepository gameRepository;
    private final GameImageMapper gameImageMapper;

    @Override
    public List<GameImageRestDto> getAll() {
        return gameImageRepository.findAll().stream().map(gameImageMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public GameImageRestDto findById(Integer gameImageId) {
        return gameImageRepository.findById(gameImageId).map(gameImageMapper::toDto).orElseThrow(GameOnException::GameImageNotFound);
    }

    @Override
    public GameImageRestDto createGameImage(GameImageDto gameImageDto) {
        Game game = gameRepository.findById(gameImageDto.getGameId()).orElseThrow(GameOnException::GameNotFound);

        if (gameImageDto.getImageLink() == null || gameImageDto.getImageLink().trim().isBlank() || gameImageDto.getImageLink().isEmpty()) {
            throw GameOnException.badRequest("ImageLinkNotFound", "Image link is missing.");
        }

        GameImage gameImage = GameImage.builder()
                .game(game)
                .imageLink(gameImageDto.getImageLink())
                .build();

        gameImage = gameImageRepository.save(gameImage);
        return gameImageMapper.toDto(gameImage);
    }

    @Override
    public GameImageRestDto updateGameImage(Integer GameImageId, GameImageDto gameImageDto) {
        GameImage gameImage = gameImageRepository.findById(GameImageId).orElseThrow(GameOnException::GameImageNotFound);

        Game game = gameRepository.findById(gameImageDto.getGameId()).orElseThrow(GameOnException::GameNotFound);

        if (gameImageDto.getImageLink() == null || gameImageDto.getImageLink().trim().isBlank() || gameImageDto.getImageLink().isEmpty()) {
            throw GameOnException.badRequest("ImageLinkNotFound", "Image link is missing.");
        }

        gameImage.setGame(game);
        gameImage.setImageLink(gameImageDto.getImageLink());

        gameImageRepository.save(gameImage);

        return gameImageMapper.toDto(gameImage);
    }

    @Override
    public void deleteGameImage(Integer gameImageId) {
        gameImageRepository.deleteById(gameImageId);
    }

    @Override
    public List<GameImageRestDto> getByGameId(Integer gameId) {
        return gameImageRepository.getByGameId(gameId).stream().map(gameImageMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<GameImageRestDto> getByGameName(String gameName) {
        return gameImageRepository.getByGameName(gameName).stream().map(gameImageMapper::toDto).collect(Collectors.toList());
    }
}
