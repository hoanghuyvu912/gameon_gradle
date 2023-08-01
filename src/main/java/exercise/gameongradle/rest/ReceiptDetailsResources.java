package exercise.gameongradle.rest;

import com.nonIt.GameOn.service.ReceiptDetailsService;
import com.nonIt.GameOn.service.createdto.ReceiptDetailsDto;
import com.nonIt.GameOn.service.customDto.GameStatisticsDto;
import com.nonIt.GameOn.service.customDto.GameWithUsedGameCodeListDto;
import com.nonIt.GameOn.service.customDto.ReceiptDetailResponseDto;
import com.nonIt.GameOn.service.customDto.RevenuePerMonthInYearDto;
import com.nonIt.GameOn.service.restdto.ReceiptDetailsRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/receipt-details")
public class ReceiptDetailsResources {
    private final ReceiptDetailsService receiptDetailsService;

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ReceiptDetailsRestDto>> getAllReceiptDetails() {
        return ResponseEntity.ok(receiptDetailsService.getAll());
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "/{receiptDetailsId}")
    public ResponseEntity<ReceiptDetailsRestDto> getReceiptDetailsById(@PathVariable("receiptDetailsId") Integer receiptDetailsId) {
        return ResponseEntity.ok(receiptDetailsService.findById(receiptDetailsId));
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public ResponseEntity<ReceiptDetailsRestDto> createReceiptDetails(@RequestBody ReceiptDetailsDto receiptDetailsDto) {
//        return ResponseEntity.ok(receiptDetailsService.createReceiptDetails(receiptDetailsDto));
//    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{receiptDetailsId}")
    public ResponseEntity<ReceiptDetailsRestDto> updateReceiptDetailsById(@PathVariable("receiptDetailsId") Integer receiptDetailsId, @RequestBody ReceiptDetailsDto receiptDetailsDto) {
        return ResponseEntity.ok(receiptDetailsService.updateReceiptDetails(receiptDetailsId, receiptDetailsDto));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{receiptDetailsId}")
    public ResponseEntity<Void> deleteReceiptDetailsById(@PathVariable("receiptDetailsId") Integer receiptDetailsId) {
        receiptDetailsService.deleteReceiptDetails(receiptDetailsId);
        return ResponseEntity.noContent().build();
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/bestseller-games")
    public ResponseEntity<List<GameWithUsedGameCodeListDto>> getBestSellerGamesBetweenDates(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate ) {
            return ResponseEntity.ok(receiptDetailsService.getBestSellerGamesBetweenDates(startDate, endDate));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/worstseller-games")
    public ResponseEntity<List<GameWithUsedGameCodeListDto>> getWorstSellerGamesBetweenDates(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate startDate, @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate ) {
        return ResponseEntity.ok(receiptDetailsService.getWorstSellerGamesBetweenDates(startDate, endDate));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/revenue-per-month-in-year")
    public ResponseEntity<RevenuePerMonthInYearDto> getRevenuePerDateBetweenDates(@RequestParam("month") Integer month, @RequestParam("year") Integer year) {
        return ResponseEntity.ok(receiptDetailsService.getRevenuePerMonthInYear(month, year));
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/game-statistics-per-month")
    public ResponseEntity<List<GameStatisticsDto>> getGameStatisticsPerMonthInYear(@RequestParam("month") Integer month, @RequestParam("year") Integer year) {
        return ResponseEntity.ok(receiptDetailsService.getGameStatisticsDto(month, year));
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/by-receipt/{receiptId}")
    public ResponseEntity<List<ReceiptDetailResponseDto>> getByReceiptId(@PathVariable("receiptId") Integer receiptId) {
        return ResponseEntity.ok(receiptDetailsService.getByReceiptId(receiptId));
    }
}
