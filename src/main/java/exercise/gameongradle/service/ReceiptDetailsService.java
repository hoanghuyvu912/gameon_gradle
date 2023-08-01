package exercise.gameongradle.service;

import exercise.gameongradle.rest.resourcesdto.SimplifiedReceiptDetailsDto;
import exercise.gameongradle.service.createdto.ReceiptDetailsDto;
import exercise.gameongradle.service.customDto.GameStatisticsDto;
import exercise.gameongradle.service.customDto.GameWithUsedGameCodeListDto;
import exercise.gameongradle.service.customDto.ReceiptDetailResponseDto;
import exercise.gameongradle.service.customDto.RevenuePerMonthInYearDto;
import exercise.gameongradle.service.restdto.ReceiptDetailsRestDto;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptDetailsService {
    List<ReceiptDetailsRestDto> getAll();

    List<SimplifiedReceiptDetailsDto> findByReceiptUserId(Integer userId);

    ReceiptDetailsRestDto findById(Integer receiptDetailsId);

    ReceiptDetailsRestDto createReceiptDetails(ReceiptDetailsDto receiptDetailsDto);

    ReceiptDetailsRestDto updateReceiptDetails(Integer receiptDetailsId, ReceiptDetailsDto receiptDetailsDto);

    void deleteReceiptDetails(Integer receiptDetailsId);

//    List<ReceiptDetailsRestDto> getRevenueOfReceiptDetailsBetweenDates(LocalDate date1, LocalDate date2);
    RevenuePerMonthInYearDto getRevenuePerMonthInYear(Integer month, Integer year);
    List<GameWithUsedGameCodeListDto> getBestSellerGamesBetweenDates(LocalDate startDate, LocalDate endDate);
    List<ReceiptDetailsDto> getReceiptDetailListBetweenDates(LocalDate startDate, LocalDate endDate);
    List<GameWithUsedGameCodeListDto> getWorstSellerGamesBetweenDates(LocalDate startDate, LocalDate endDate);

    List<GameStatisticsDto> getGameStatisticsDto(Integer month, Integer year);
    List<ReceiptDetailResponseDto> getByReceiptId(Integer receiptId);

}
