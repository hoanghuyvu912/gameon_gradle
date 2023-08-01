package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.Game;
import com.nonIt.GameOn.entity.Rating;
import com.nonIt.GameOn.entity.User;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.GameRepository;
import com.nonIt.GameOn.repository.RatingRepository;
import com.nonIt.GameOn.repository.UserRepository;
import com.nonIt.GameOn.rest.resourcesdto.SimplifiedRatingDto;
import com.nonIt.GameOn.service.RatingService;
import com.nonIt.GameOn.service.createdto.RatingDto;
import com.nonIt.GameOn.service.mapper.GameMapper;
import com.nonIt.GameOn.service.mapper.RatingMapper;
import com.nonIt.GameOn.service.restdto.RatingRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    @Override
    public List<RatingRestDto> getAll() {
        return ratingRepository.findAll().stream().map(ratingMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RatingRestDto findById(Integer ratingId) {
        return ratingRepository.findById(ratingId).map(ratingMapper::toDto).orElseThrow(GameOnException::RatingNotFound);
    }

    @Override
    public SimplifiedRatingDto createRating(RatingDto ratingDto) {
        User user = userRepository.findById(ratingDto.getUserId()).orElseThrow(GameOnException::UserNotFound);
        Game game = gameRepository.findById(ratingDto.getGameId()).orElseThrow(GameOnException::GameNotFound);
        if (ratingDto.getRating() > 5 || ratingDto.getRating() < 1) {
            throw GameOnException.badRequest("InvalidRating", "Rating must be an integer between 1 and 5.");
        }
        Rating rating = Rating.builder()
                .user(user)
                .game(game)
                .rating(ratingDto.getRating())
                .build();
        rating = ratingRepository.save(rating);

        return ratingMapper.toSimplifiedDto(rating);
    }

    @Override
    public RatingRestDto updateRating(Integer ratingId, RatingDto ratingDto) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(GameOnException::RatingNotFound);
        User user = userRepository.findById(ratingDto.getUserId()).orElseThrow(GameOnException::UserNotFound);
        Game game = gameRepository.findById(ratingDto.getGameId()).orElseThrow(GameOnException::GameNotFound);
        if (ratingDto.getRating() == null) {
            throw GameOnException.badRequest("RatingNotFound", "Rating is missing.");
        }

        if (ratingDto.getRating() > 5 || ratingDto.getRating() < 1) {
            throw GameOnException.badRequest("InvalidRating", "Rating must be an integer between 1 and 5.");
        }
        rating.setRating(ratingDto.getRating());
        rating.setUser(user);
        rating.setGame(game);
        rating = ratingRepository.save(rating);
        return ratingMapper.toDto(rating);
    }

    @Override
    public void deleteRating(Integer ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    @Override
    public List<RatingRestDto> getByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(GameOnException::UserNotFound);
        return ratingRepository.getRatingByUserId(userId).stream().map(ratingMapper::toDto).collect(Collectors.toList());
    }

//    @Override
//    public List<Rating> demo() {
//        return ratingRepository.findTop1ByOrderByRatingDescByGroupByGame();
//    }

//    @Override
//    public List<GameRestDto> getGameByRatingBetweenAndReleasedDateBetween(Integer rating1, Integer rating2, LocalDate date1, LocalDate date2) {
//        return ratingRepository.findAll().stream()
//                .filter(r -> r.getRating() < rating2)
//                .filter(r -> r.getRating() > rating1)
//                .map(Rating::getGame)
//                .filter(game -> game.getReleasedDate().isAfter(date1))
//                .filter(game -> game.getReleasedDate().isBefore(date2))
//                .distinct()
//                .map(gameMapper::toDto)
//                .collect(Collectors.toList());
//    }
}
