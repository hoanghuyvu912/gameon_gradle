package exercise.gameongradle.service;

import exercise.gameongradle.rest.resourcesdto.ReceiptCreateDto;
import exercise.gameongradle.service.createdto.ReceiptDto;
import exercise.gameongradle.service.customDto.ReceiptResponseDto;
import exercise.gameongradle.service.restdto.ReceiptRestDto;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptService {
    List<ReceiptResponseDto> getAll();

    ReceiptRestDto findById(Integer receiptId);

    List<ReceiptRestDto> findByReceiptDateAfter(LocalDate date);

    List<ReceiptRestDto> findByReceiptDateBefore(LocalDate date);

    ReceiptRestDto createReceipt(ReceiptCreateDto receiptCreateDto);

    ReceiptRestDto updateReceipt(Integer receiptId, ReceiptDto receiptDto);

    void deleteReceipt(Integer receiptId);

    List<ReceiptRestDto> getByUserId(Integer userId);
}
