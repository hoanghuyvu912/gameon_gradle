package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.*;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.GameCodeRepository;
import com.nonIt.GameOn.repository.GameRepository;
import com.nonIt.GameOn.repository.ReceiptDetailsRepository;
import com.nonIt.GameOn.repository.ReceiptRepository;
import com.nonIt.GameOn.rest.resourcesdto.SimplifiedGameDto;
import com.nonIt.GameOn.rest.resourcesdto.SimplifiedReceiptDetailsDto;
import com.nonIt.GameOn.service.ReceiptDetailsService;
import com.nonIt.GameOn.service.createdto.ReceiptDetailsDto;
import com.nonIt.GameOn.service.customDto.GameStatisticsDto;
import com.nonIt.GameOn.service.customDto.GameWithUsedGameCodeListDto;
import com.nonIt.GameOn.service.customDto.ReceiptDetailResponseDto;
import com.nonIt.GameOn.service.customDto.RevenuePerMonthInYearDto;
import com.nonIt.GameOn.service.mapper.GameCodeMapper;
import com.nonIt.GameOn.service.mapper.ReceiptDetailsMapper;
import com.nonIt.GameOn.service.restdto.ReceiptDetailsRestDto;
import com.nonIt.GameOn.utils.ConvertToSimplifiedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReceiptDetailsServiceImpl implements ReceiptDetailsService {
    private final ReceiptDetailsRepository receiptDetailsRepository;
    private final ReceiptRepository receiptRepository;
    private final GameRepository gameRepository;
    private final ReceiptDetailsMapper receiptDetailsMapper;
    private final GameCodeRepository gameCodeRepository;
    private final GameCodeMapper gameCodeMapper;

    private final ConvertToSimplifiedDto convertToSimplifiedDto;

    @Override
    public List<ReceiptDetailsRestDto> getAll() {
        return receiptDetailsRepository.findAll().stream().map(receiptDetailsMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<SimplifiedReceiptDetailsDto> findByReceiptUserId(Integer userId) {
        return receiptDetailsRepository.findByReceiptUserId(userId).stream().map(receiptDetailsMapper::toSimplifiedDto).collect(Collectors.toList());
    }

    @Override
    public ReceiptDetailsRestDto findById(Integer receiptDetailsId) {
        return receiptDetailsRepository.findById(receiptDetailsId).map(receiptDetailsMapper::toDto).orElseThrow(GameOnException::ReceiptDetailsNotFound);
    }

    @Override
    public ReceiptDetailsRestDto createReceiptDetails(ReceiptDetailsDto receiptDetailsDto) {
        Receipt receipt = receiptRepository.findById(receiptDetailsDto.getReceiptId()).orElseThrow(GameOnException::ReceiptNotFound);

        GameCode gameCode = gameCodeRepository.findById(receiptDetailsDto.getGameCodeId()).orElseThrow(GameOnException::GameCodeNotFound);

        ReceiptDetails receiptDetails = ReceiptDetails.builder()
                .receipt(receipt)
                .gameCode(gameCode)
                .build();
        receiptDetails = receiptDetailsRepository.save(receiptDetails);

        return receiptDetailsMapper.toDto(receiptDetails);
    }

    @Override
    public ReceiptDetailsRestDto updateReceiptDetails(Integer receiptDetailsId, ReceiptDetailsDto receiptDetailsDto) {
        ReceiptDetails receiptDetails = receiptDetailsRepository.findById(receiptDetailsId).orElseThrow(GameOnException::ReceiptDetailsNotFound);
        Receipt receipt = receiptRepository.findById(receiptDetailsDto.getReceiptId()).orElseThrow(GameOnException::ReceiptNotFound);
        GameCode gameCode = gameCodeRepository.findById(receiptDetailsDto.getGameCodeId()).orElseThrow(GameOnException::GameCodeNotFound);
//
//        if (receipt.getReceiptDate().isBefore(game.getReleasedDate())) {
//            throw GameOnException.badRequest("InvalidReceiptDate", "The receipt date cannot be before a game's released date. Please choose a different game, or a different receipt.");
//        }
        receiptDetails.setReceipt(receipt);
        receiptDetails.setGameCode(gameCode);
        receiptDetails = receiptDetailsRepository.save(receiptDetails);
        return receiptDetailsMapper.toDto(receiptDetails);
    }

    @Override
    public void deleteReceiptDetails(Integer receiptDetailsId) {
        receiptDetailsRepository.deleteById(receiptDetailsId);
    }

    @Override
    public RevenuePerMonthInYearDto getRevenuePerMonthInYear(Integer month, Integer year) {
        return receiptDetailsRepository.getRevenuePerMonthInYear(month,year);
    }

    @Override
    public List<GameWithUsedGameCodeListDto> getBestSellerGamesBetweenDates(LocalDate startDate, LocalDate endDate) {
        isValidDate(startDate, endDate);
        List<SimplifiedGameDto> gameList = getGamesSoldBetweenDates(startDate, endDate).stream().map(convertToSimplifiedDto::convertGameEntityToSimplifiedDto).collect(Collectors.toList());

        Map<SimplifiedGameDto, Long> gamesWithUsedGameCodeList = getGameLongMap(gameList);

        return gamesWithUsedGameCodeList.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<SimplifiedGameDto, Long>::getValue).reversed())
                .limit(5)
                .map(entry -> new GameWithUsedGameCodeListDto(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toList());
    }

    private List<Game> getGamesSoldBetweenDates(LocalDate startDate, LocalDate endDate) {
        isValidDate(startDate, endDate);
        List<GameCode> usedGameCodes = receiptDetailsRepository.findByReceiptReceiptDateBetween(startDate, endDate)
                .stream()
                .map(ReceiptDetails::getGameCode)
                .collect(Collectors.toList());
        return usedGameCodes.stream().map(GameCode::getGame).collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDetailsDto> getReceiptDetailListBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<ReceiptDetailsDto> receiptDetailsDtos = new ArrayList<>();

        isValidDate(startDate, endDate);

        receiptDetailsRepository.findByReceiptReceiptDateBetween(startDate, endDate)
                .forEach(receiptDetails -> {
                    ReceiptDetailsDto receiptDetailsDto = new ReceiptDetailsDto(
                            receiptDetails.getReceipt().getId(),
                            receiptDetails.getGameCode().getId(),
                            receiptDetails.getGamePrice()
                    );
                    receiptDetailsDtos.add(receiptDetailsDto);
                });
        return receiptDetailsDtos;
    }

    private static void isValidDate(LocalDate startDate, LocalDate endDate) {
        if(startDate.isAfter(LocalDate.now()) || endDate.isAfter(LocalDate.now())) {
            throw GameOnException.badRequest("BadRequest","Invalid Date");
        }
    }

    @Override
    public List<GameWithUsedGameCodeListDto> getWorstSellerGamesBetweenDates(LocalDate startDate, LocalDate endDate) {
        isValidDate(startDate, endDate);

        List<SimplifiedGameDto> gameList = getGamesSoldBetweenDates(startDate, endDate).stream().map(convertToSimplifiedDto::convertGameEntityToSimplifiedDto).collect(Collectors.toList());

        Map<SimplifiedGameDto, Long> gamesWithUsedGameCodeList = getGameLongMap(gameList);

        return gamesWithUsedGameCodeList.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<SimplifiedGameDto, Long>::getValue))
                .limit(5)
                .map(entry -> new GameWithUsedGameCodeListDto(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GameStatisticsDto> getGameStatisticsDto(Integer month, Integer year) {
        return receiptDetailsRepository.getGameStatisticsPerMonth(month, year);
    }

    @Override
    public List<ReceiptDetailResponseDto> getByReceiptId(Integer receiptId) {
        return receiptDetailsRepository.findByReceiptId(receiptId);
    }

    private static Map<SimplifiedGameDto, Long> getGameLongMap(List<SimplifiedGameDto> gameList) {
        Map<SimplifiedGameDto, Long> gamesWithUsedGameCodeList = new HashMap<>();
        for (SimplifiedGameDto simplifiedGameDto : gameList) {
            gamesWithUsedGameCodeList.put(simplifiedGameDto, 0L);
        }

        for (Map.Entry<SimplifiedGameDto, Long> entry : gamesWithUsedGameCodeList.entrySet()) {
            SimplifiedGameDto key = entry.getKey();
            Long value = entry.getValue();

            for (SimplifiedGameDto simplifiedGameDto : gameList) {
                if (simplifiedGameDto.getId().equals(key.getId())) {
                    value++;
                    entry.setValue(value);
                }
            }
        }
        return gamesWithUsedGameCodeList;
    }
}
