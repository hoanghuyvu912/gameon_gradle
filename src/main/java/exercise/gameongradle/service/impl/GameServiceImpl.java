package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.*;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.*;
import com.nonIt.GameOn.rest.resourcesdto.*;
import com.nonIt.GameOn.security.jwt.JwtUtils;
import com.nonIt.GameOn.service.GameService;
import com.nonIt.GameOn.service.createdto.GameDto;
import com.nonIt.GameOn.service.customDto.GameLibraryDto;
import com.nonIt.GameOn.service.customDto.GameSearchDto;
import com.nonIt.GameOn.service.customDto.GameWithUsedGameCodeListDto;
import com.nonIt.GameOn.service.mapper.*;
import com.nonIt.GameOn.service.restdto.GameRestDto;
import exercise.gameongradle.entity.*;
import exercise.gameongradle.exception.GameOnException;
import exercise.gameongradle.repository.*;
import exercise.gameongradle.rest.resourcesdto.SimplifiedGameDto;
import exercise.gameongradle.service.createdto.GameDto;
import exercise.gameongradle.service.customDto.GameLibraryDto;
import exercise.gameongradle.service.customDto.GameWithUsedGameCodeListDto;
import exercise.gameongradle.service.mapper.*;
import exercise.gameongradle.service.restdto.GameRestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    @Autowired
//    private final JwtUtils jwtUtils;
    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;
    private final PublisherRepository publisherRepository;
    private final GameCodeRepository gameCodeRepository;
    private final RatingRepository ratingRepository;
    private final ReceiptDetailsRepository receiptDetailsRepository;
    private final GameMapper gameMapper;
    private final RatingMapper ratingMapper;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final GameImageMapper gameImageMapper;
    private final GameSubGenreMapper gameSubGenreMapper;
    private final GameGenreMapper gameGenreMapper;

    //CRUD Services
    @Override
    public List<SimplifiedGameDto> getAll() {
//        return gameRepository.findAllByOrderByIdAsc().stream().map(gameMapper::toSimplifiedDto).collect(Collectors.toList());
        List<Game> gameList = gameRepository.findAllByOrderByIdAsc();
        List<SimplifiedGameDto> simplifiedGameDtoList = new ArrayList<>();
        for (Game game : gameList) {
            simplifiedGameDtoList.add(convertGameEntityToSimplifiedDto(game));
        }
        return simplifiedGameDtoList;
    }

    @Override
    public SimplifiedGameDto createGame(GameDto gameDto) {
        Developer developer = developerRepository.findById(gameDto.getDeveloperId()).orElseThrow(GameOnException::DeveloperNotFound);
        Publisher publisher = publisherRepository.findById(gameDto.getPublisherId()).orElseThrow(GameOnException::PublisherNotFound);

        if (gameDto.getName() == null || gameDto.getName().trim().isBlank() || gameDto.getName().isEmpty()) {
            throw GameOnException.badRequest("GameNameNotFound", "Game's name is missing");
        }
        if (gameDto.getThumbnail() == null || gameDto.getThumbnail().trim().isBlank() || gameDto.getThumbnail().isEmpty()) {
            throw GameOnException.badRequest("GameThumbnailNotFound", "Game's thumbnail is missing");
        }
        if (gameDto.getDescription() == null || gameDto.getDescription().trim().isBlank() || gameDto.getDescription().isEmpty()) {
            throw GameOnException.badRequest("GameDescriptionNotFound", "Game's description is missing");
        }
        if (gameDto.getTrailer() == null || gameDto.getTrailer().trim().isBlank() || gameDto.getTrailer().isEmpty()) {
            throw GameOnException.badRequest("GameTrailerNotFound", "Game's trailer is missing");
        }
        if (gameDto.getReleasedDate().isAfter(LocalDate.now())) {
            throw GameOnException.badRequest("InvalidReleasedDate", "Released date cannot be after current date.");
        }
        if (gameDto.getReleasedDate().isBefore(developer.getEstablishedDate())) {
            throw GameOnException.badRequest("InvalidReleasedDate", "Released date cannot be before developer's established date.");
        }
        if (gameDto.getReleasedDate().isBefore(publisher.getEstablishedDate())) {
            throw GameOnException.badRequest("InvalidReleasedDate", "Released date cannot be before publisher's established date.");
        }
        if (gameDto.getSystemReq() == null || gameDto.getSystemReq().trim().isBlank() || gameDto.getSystemReq().isEmpty()) {
            throw GameOnException.badRequest("GameSystemReqNotFound", "Game's system requirement is missing");
        }
        if (gameDto.getPrice() < 0) {
            throw GameOnException.badRequest("InvalidPrice", "Game's price must be a positive number.");
        }

        Game game = Game.builder()
                .name(gameDto.getName())
                .thumbnail(gameDto.getThumbnail())
                .description(gameDto.getDescription())
                .trailer(gameDto.getTrailer())
                .releasedDate(gameDto.getReleasedDate())
                .systemReq(gameDto.getSystemReq())
                .price(gameDto.getPrice())
                .developer(developer)
                .publisher(publisher)
                .build();

        game = gameRepository.save(game);

        return convertGameEntityToSimplifiedDto(game);
    }

    @Override
    public GameRestDto updateGame(Integer gameId, GameDto gameDto) {
        Game game = gameRepository.findById(gameId).orElseThrow(GameOnException::GameNotFound);

        if (gameDto.getName() != null) {
            if (gameDto.getName().trim().isBlank() || gameDto.getName().isEmpty()) {
                throw GameOnException.badRequest("GameNameNotFound", "Game's name is missing");
            }
        }
        if (gameDto.getThumbnail() != null) {
            if (gameDto.getThumbnail().trim().isBlank() || gameDto.getThumbnail().isEmpty()) {
                throw GameOnException.badRequest("GameThumbnailNotFound", "Game's thumbnail is missing");
            }
        }
        if (gameDto.getDescription() != null) {
            if (gameDto.getDescription().trim().isBlank() || gameDto.getDescription().isEmpty()) {
                throw GameOnException.badRequest("GameDescriptionNotFound", "Game's description is missing");
            }
        }
        if (gameDto.getTrailer() != null) {
            if (gameDto.getTrailer().trim().isBlank() || gameDto.getTrailer().isEmpty()) {
                throw GameOnException.badRequest("GameTrailerNotFound", "Game's trailer is missing");
            }
        }
        if (gameDto.getReleasedDate() != null) {
            if (gameDto.getReleasedDate().isAfter(LocalDate.now())) {
                throw GameOnException.badRequest("InvalidReleasedDate", "Released date cannot be after current date.");
            }
            if (gameDto.getReleasedDate().isBefore(game.getDeveloper().getEstablishedDate())) {
                throw GameOnException.badRequest("InvalidReleasedDate", "Released date cannot be before developer's established date.");
            }
            if (gameDto.getReleasedDate().isBefore(game.getPublisher().getEstablishedDate())) {
                throw GameOnException.badRequest("InvalidReleasedDate", "Released date cannot be before publisher's established date.");
            }
        }
        if (gameDto.getSystemReq() != null) {
            if (gameDto.getSystemReq().trim().isBlank() || gameDto.getSystemReq().isEmpty()) {
                throw GameOnException.badRequest("GameSystemReqNotFound", "Game's system requirement is missing");
            }
        }
        if (gameDto.getPrice() != null) {
            if (gameDto.getPrice() < 0) {
                throw GameOnException.badRequest("InvalidPrice", "Game's price must be a positive number.");
            }
        }

        if (gameDto.getDeveloperId() != null) {
            Developer developer = developerRepository.findById(gameDto.getDeveloperId()).orElseThrow(GameOnException::DeveloperNotFound);
            game.setDeveloper(developer);
        }

        if (gameDto.getPublisherId() != null) {
            Publisher publisher = publisherRepository.findById(gameDto.getPublisherId()).orElseThrow(GameOnException::PublisherNotFound);
            game.setPublisher(publisher);
        }

        gameMapper.mapFromDto(gameDto, game);
        game = gameRepository.save(game);
        return gameMapper.toDto(game);
    }

    @Override
    public void deleteGame(Integer gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(GameOnException::GameNotFound);
        gameRepository.deleteById(gameId);
    }

    // Find featured games
    @Override
    public List<SimplifiedGameDto> getFeaturedGame() {
        return gameRepository.findByReleasedDateBetween(LocalDate.now().minusMonths(6), LocalDate.now()).stream().map(gameMapper::toSimplifiedDto).collect(Collectors.toList());
    }

    @Override
    public List<GameWithUsedGameCodeListDto> getRecentBestSellerGames() {
        List<SimplifiedGameDto> recentSoldGames = receiptDetailsRepository.findByReceiptReceiptDateBetween(LocalDate.now().minusYears(2), LocalDate.now())
                .stream()
                .map(ReceiptDetails::getGameCode)
                .map(GameCode::getGame)
                .map(this::convertGameEntityToSimplifiedDto)
                .collect(Collectors.toList());
        Map<SimplifiedGameDto, Long> gamesWithUsedGameCodeList = new HashMap<>();
        for (SimplifiedGameDto simplifiedGameDto : recentSoldGames) {
            gamesWithUsedGameCodeList.put(simplifiedGameDto, 0L);
        }

        for (Map.Entry<SimplifiedGameDto, Long> entry : gamesWithUsedGameCodeList.entrySet()) {
            SimplifiedGameDto key = entry.getKey();
            Long value = entry.getValue();

            for (SimplifiedGameDto simplifiedGameDto : recentSoldGames) {
                if (simplifiedGameDto.getId().equals(key.getId())) {
                    value++;
                    entry.setValue(value);
                }
            }
        }
        return gamesWithUsedGameCodeList.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<SimplifiedGameDto, Long>::getValue).reversed())
                .limit(5)
                .map(entry -> new GameWithUsedGameCodeListDto(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GameWithUsedGameCodeListDto> getRecentWorstSellerGames() {
        List<SimplifiedGameDto> recentSoldGames = receiptDetailsRepository.findByReceiptReceiptDateBetween(LocalDate.now().minusYears(2), LocalDate.now())
                .stream()
                .map(ReceiptDetails::getGameCode)
                .map(GameCode::getGame)
                .map(this::convertGameEntityToSimplifiedDto)
                .collect(Collectors.toList());
        Map<SimplifiedGameDto, Long> gamesWithUsedGameCodeList = new HashMap<>();
        for (SimplifiedGameDto simplifiedGameDto : recentSoldGames) {
            gamesWithUsedGameCodeList.put(simplifiedGameDto, 0L);
        }

        for (Map.Entry<SimplifiedGameDto, Long> entry : gamesWithUsedGameCodeList.entrySet()) {
            SimplifiedGameDto key = entry.getKey();
            Long value = entry.getValue();

            for (SimplifiedGameDto simplifiedGameDto : recentSoldGames) {
                if (simplifiedGameDto.getId().equals(key.getId())) {
                    value++;
                    entry.setValue(value);
                }
            }
        }
        return gamesWithUsedGameCodeList.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<SimplifiedGameDto, Long>::getValue))
                .limit(5)
                .map(entry -> new GameWithUsedGameCodeListDto(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toList());
    }

    // Find best-seller games between period
//    @Override
//    public List<GameRestDto> getBestSellerGamesBetweenAPeriod() {
//        return gameRepository.findByReleasedDateBetween(LocalDate.now().minusMonths(6), LocalDate.now()).stream()
//                .filter(g -> g.getRatingList().stream().map(rating -> rating.getRating()).anyMatch(g.getRatingList().stream().filter(rating -> rating.getRating() >= 4)))
//                .map(gameMapper::toDto)
//                .collect(Collectors.toList());
//    }

    //Find by name
    @Override
    public List<GameRestDto> findByNameIgnoreCaseContaining(String name) {
        log.info("Search for a game that name contains {}", name);
        if (name == null || name.trim().isBlank() || name.isEmpty()) {
            throw GameOnException.badRequest("GameNameNotFound", "Game's name search input is missing");
        }
        return gameRepository.findByNameIgnoreCaseContaining(name).stream().map(gameMapper::toDto).collect(Collectors.toList());
    }


    //Find by name and released date

    //Find by foreign keys
    @Override
    public List<GameRestDto> getByDeveloperId(Integer developerId) {
        return gameRepository.getByDeveloperId(developerId).stream().map(gameMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<GameRestDto> getByPublisherId(Integer publisherId) {
        return gameRepository.getByPublisherId(publisherId).stream().map(gameMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<GameLibraryDto> getByUser(String authorization) {
        System.out.println(authorization);
        User user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(authorization)).orElseThrow(GameOnException::UserNotFound);

        List<GameLibraryDto> gameLibraryDtos = gameRepository.getByUserId(user.getId());

        return gameLibraryDtos;
    }

    @Override
    public List<GameRestDto> getByGenreId(Integer genreId) {
        return gameRepository.getByGenreId(genreId).stream().map(gameMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<GameRestDto> getByGenreName(String genreName) {
        return gameRepository.getByGenreName(genreName).stream().map(gameMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<GameRestDto> getBySubGenreId(Integer subGenreId) {
        return gameRepository.getBySubGenreId(subGenreId).stream().map(gameMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<GameRestDto> getBySubGenreName(String subGenreName) {
        return gameRepository.getBySubGenreName(subGenreName).stream().map(gameMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SimplifiedGameDto findById(Integer gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(GameOnException::GameNotFound);
        return convertGameEntityToSimplifiedDto(game);
    }


    //Custom queries
    @Override
    public List<GameRestDto> getByRatingAndReleasedDateBetween(Integer rating1, Integer rating2, LocalDate date1, LocalDate date2) {
        return ratingRepository.findAll().stream()
                .filter(r -> r.getRating() > rating1)
                .filter(r -> r.getRating() < rating2)
                .map(Rating::getGame)
                .filter(game -> game.getReleasedDate().isAfter(date1))
                .filter(game -> game.getReleasedDate().isBefore(date2))
                .distinct()
                .map(gameMapper::toDto)
                .collect(Collectors.toList());
    }


    //TEST ADVANCED SEARCH
    public List<GameRestDto> getGamesByGameSearchDto(GameSearchDto gameSearchDto) {
        if (gameSearchDto == null) {
            throw GameOnException.badRequest("MissingSearchCriteria", "Search criteria not found.");
        }
        return gameRepository.findGamesByDto(gameSearchDto).stream().map(gameMapper::toDto).collect(Collectors.toList());
    }

    public Integer getUsedGameCodeListOfGame(Integer gameId) {
        return (Integer) (int) gameCodeRepository.findAll().stream()
                .filter(gameCode -> gameId.equals(gameCode.getGame().getId()))
                .filter(gameCode -> gameCode.getGameCodeStatus().equals(GameCodeStatus.Used)).count();
    }

    public SimplifiedGameDto convertGameEntityToSimplifiedDto(Game game) {

        SimplifiedGameDto simplifiedGameDto = gameMapper.toSimplifiedDto(game);
        List<SimplifiedCommentDto> simplifiedCommentDtoList = game.getCommentList().stream()
                .map(commentMapper::toSimplifiedDto)
                .collect(Collectors.toList());

        List<SimplifiedRatingDto> simplifiedRatingDtoList = game.getRatingList().stream()
                .map(ratingMapper::toSimplifiedDto)
                .collect(Collectors.toList());

        List<SimplifiedGameImageDto> simplifiedGameImageDtoList = game.getGameImageList().stream()
                .map(gameImageMapper::toSimplifiedDto)
                .collect(Collectors.toList());

        List<SimplifiedGameGenreDto> simplifiedGameGenreDtoList = game.getGameGenreList().stream()
                .map(gameGenreMapper::toSimplifiedDto)
                .collect(Collectors.toList());

        List<SimplifiedGameSubGenreDto> simplifiedGameSubGenreDtoList = game.getGameSubGenreList().stream()
                .map(gameSubGenreMapper::toSimplifiedDto)
                .collect(Collectors.toList());


        simplifiedGameDto.setSimplifiedCommentDtoList(simplifiedCommentDtoList);
        simplifiedGameDto.setSimplifiedRatingDtoList(simplifiedRatingDtoList);
        simplifiedGameDto.setSimplifiedGameGenreDtoList(simplifiedGameGenreDtoList);
        simplifiedGameDto.setSimplifiedGameSubGenreDtoList(simplifiedGameSubGenreDtoList);
        simplifiedGameDto.setSimplifiedGameImageDtoList(simplifiedGameImageDtoList);
        return simplifiedGameDto;
    }
}
