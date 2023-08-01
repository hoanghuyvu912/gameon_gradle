package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.*;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.*;
import com.nonIt.GameOn.rest.resourcesdto.ReceiptCreateDto;
import com.nonIt.GameOn.security.jwt.JwtUtils;
import com.nonIt.GameOn.service.ReceiptService;
import com.nonIt.GameOn.service.createdto.ReceiptDto;
import com.nonIt.GameOn.service.customDto.ReceiptResponseDto;
import com.nonIt.GameOn.service.mapper.ReceiptMapper;
import com.nonIt.GameOn.service.restdto.ReceiptRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private final JwtUtils jwtUtils;

    private final ReceiptRepository receiptRepository;
    private final UserRepository userRepository;
    private final ReceiptMapper receiptMapper;
    private final GameRepository gameRepository;
    private final ReceiptDetailsRepository receiptDetailsRepository;
    private final GameCodeRepository gameCodeRepository;


    @Override
    public List<ReceiptResponseDto> getAll() {
        return receiptRepository.findAll().stream().map(receiptMapper::toResponseDto).collect(Collectors.toList());
    }

    @Override
    public ReceiptRestDto findById(Integer receiptId) {
        return receiptRepository.findById(receiptId).map(receiptMapper::toDto).orElseThrow(GameOnException::ReceiptNotFound);
    }

    @Override
    public List<ReceiptRestDto> findByReceiptDateAfter(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw GameOnException.badRequest("InvalidDate", "Query date cannot be after current date.");
        }
        return receiptRepository.findByReceiptDateAfter(date).stream().map(receiptMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ReceiptRestDto> findByReceiptDateBefore(LocalDate date) {
        return receiptRepository.findByReceiptDateBefore(date).stream().map(receiptMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public ReceiptRestDto createReceipt(ReceiptCreateDto receiptCreateDto) {
        User user = userRepository.findById(receiptCreateDto.getUserId()).orElseThrow(GameOnException::UserNotFound);
        List<Integer> gamesIdListOfUser = receiptDetailsRepository.findByReceiptUserId(receiptCreateDto.getUserId()).stream()
                .map(ReceiptDetails::getGameCode)
                .map(GameCode::getGame)
                .map(Game::getId)
                .distinct()
                .collect(Collectors.toList());

        if (gamesIdListOfUser.stream().anyMatch(receiptCreateDto.getGameIdList().stream().collect(Collectors.toList())::contains)) {
            throw GameOnException.badRequest("CannotBuyGame", "User already owns this game.");
        }

        Receipt receipt = Receipt.builder().user(user).build();
        List<ReceiptDetails> receiptDetailsList = new ArrayList<>();
        Double totalPriceOfCart = 0D;

        for (Integer gameId : receiptCreateDto.getGameIdList()) {
            Game game = gameRepository.findById(gameId).orElseThrow(GameOnException::GameNotFound);
            totalPriceOfCart += game.getPrice();
            Optional<GameCode> gameCode = gameCodeRepository.findByGameId(gameId).stream().filter(gc -> GameCodeStatus.Available.equals(gc.getGameCodeStatus())).findFirst();

            ReceiptDetails receiptDetails = ReceiptDetails.builder()
                    .receipt(receipt)
                    .gamePrice(game.getPrice())
                    .build();

            if (gameCode.isPresent()) {
                receiptDetails.setGameCode(gameCode.get());
                gameCode.get().setGameCodeStatus(GameCodeStatus.Used);
            } else {
                throw GameOnException.badRequest("CannotGetGameCode", "The current game doesn't have any code left.");
            }
            receiptDetailsList.add(receiptDetails);
        }
        if (totalPriceOfCart > user.getBalance()) {
            throw GameOnException.badRequest("InsufficientBalance", "Insufficient balance. \nYour current balance: $" + user.getBalance() + ". \nTotal price of cart: $" + totalPriceOfCart);
        }

        user.setBalance(user.getBalance() - totalPriceOfCart);
        receipt.setReceiptDetailsList(receiptDetailsList);
        receipt = receiptRepository.save(receipt);
        return receiptMapper.toDto(receipt);
    }

//    @Override
//    public ReceiptRestDto createReceipt(ReceiptCreateDto receiptCreateDto) {
//        User user = userRepository.findById(receiptCreateDto.getUserId()).orElseThrow(GameOnException::UserNotFound);
//        List<Integer> gamesIdListOfUser = receiptDetailsRepository.findByReceiptUserId(receiptCreateDto.getUserId()).stream()
//                .map(receiptDetailsMapper::toSimplifiedDto)
//                .map(SimplifiedReceiptDetailsDto::getGameId).collect(Collectors.toList());
//        Receipt receipt = Receipt.builder()
//                .user(user)
//                .build();
//
//        List<ReceiptDetails> receiptDetailsList = new ArrayList<>();
//        Double totalPriceOfCart = 0D;
//
//        for (Integer gameId : receiptCreateDto.getGameIdList()) {
//            for (Integer gameIdOfUser : gamesIdListOfUser) {
//                if (Objects.equals(gameId, gameIdOfUser)) {
//                    throw GameOnException.badRequest("CannotBuyGame", "User has already bought this game!");
//                }
//            }
//            ReceiptDetails receiptDetails = new ReceiptDetails();
//            Game game = gameRepository.findById(gameId).orElseThrow(GameOnException::GameNotFound);
//            totalPriceOfCart += game.getPrice();
//            receiptDetails.setReceipt(receipt);
//            receiptDetails.setGame(game);
//            receiptDetailsList.add(receiptDetails);
//        }
//        if (totalPriceOfCart > user.getBalance()) {
//            throw GameOnException.badRequest("InsufficientBalance", "Insufficient balance. \nYour current balance: $" + user.getBalance() + ". \nTotal price of cart: $" + totalPriceOfCart);
//        }
//
//        user.setBalance(user.getBalance() - totalPriceOfCart);
//
//        receipt.setReceiptDetailsList(receiptDetailsList);
//
//        receipt = receiptRepository.save(receipt);
//
//        return receiptMapper.toDto(receipt);
//    }


    @Override
    public ReceiptRestDto updateReceipt(Integer receiptId, ReceiptDto receiptDto) {
        Receipt receipt = receiptRepository.findById(receiptId).orElseThrow(GameOnException::ReceiptNotFound);
        User user = userRepository.findById(receiptDto.getUserId()).orElseThrow(GameOnException::UserNotFound);
        if (receiptDto.getReceiptDate() != null) {
            if (receiptDto.getReceiptDate().isAfter(LocalDate.now())) {
                throw GameOnException.badRequest("InvalidReceiptDate", "Receipt date cannot be after current date.");
            }
        }
        receiptMapper.mapFromDto(receiptDto, receipt);
        receipt.setUser(user);
        receipt = receiptRepository.save(receipt);

        return receiptMapper.toDto(receipt);
    }

    @Override
    public void deleteReceipt(Integer receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId).orElseThrow(GameOnException::ReceiptNotFound);
        receiptRepository.deleteById(receiptId);
    }

    @Override
    public List<ReceiptRestDto> getByUserId(Integer userId) {
        return receiptRepository.findByUserId(userId).stream().map(receiptMapper::toDto).collect(Collectors.toList());
    }
}
