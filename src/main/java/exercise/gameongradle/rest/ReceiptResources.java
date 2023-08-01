package exercise.gameongradle.rest;

import com.nonIt.GameOn.rest.resourcesdto.ReceiptCreateDto;
import com.nonIt.GameOn.service.ReceiptService;
import com.nonIt.GameOn.service.createdto.ReceiptDto;
import com.nonIt.GameOn.service.customDto.ReceiptResponseDto;
import com.nonIt.GameOn.service.restdto.ReceiptRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/receipts")
//@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class ReceiptResources {
    private final ReceiptService receiptService;

    @GetMapping
    public ResponseEntity<List<ReceiptResponseDto>> getAllReceipt() {
        return ResponseEntity.ok(receiptService.getAll());
    }

    @GetMapping(value = "/{receiptId}")
    public ResponseEntity<ReceiptRestDto> getReceiptById(@PathVariable("receiptId") Integer receiptId) {
        return ResponseEntity.ok(receiptService.findById(receiptId));
    }

    @GetMapping("/receipt-date-after")
    public ResponseEntity<List<ReceiptRestDto>> getByReceiptDateAfter(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(receiptService.findByReceiptDateAfter(date));
    }

    @GetMapping("/receipt-date-before")
    public ResponseEntity<List<ReceiptRestDto>> getByReceiptDateBefore(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(receiptService.findByReceiptDateBefore(date));
    }

    @PostMapping
    public ResponseEntity<ReceiptRestDto> createReceipt(@RequestBody ReceiptCreateDto receiptCreateDto) {
//        System.out.println(authorization);
        return ResponseEntity.ok(receiptService.createReceipt(receiptCreateDto));
    }

    @PutMapping(value = "/{receiptId}")
    public ResponseEntity<ReceiptRestDto> updateReceiptById(@PathVariable("receiptId") Integer receiptId, @RequestBody ReceiptDto receiptDto) {
        return ResponseEntity.ok(receiptService.updateReceipt(receiptId, receiptDto));
    }

    @DeleteMapping(value = "/{receiptId}")
    public ResponseEntity<Void> deleteReceiptById(@PathVariable("receiptId") Integer receiptId) {
        receiptService.deleteReceipt(receiptId);
        return ResponseEntity.noContent().build();
    }

//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping(value = "/by-user/{userId}")
    public ResponseEntity<List<ReceiptRestDto>> getReceiptByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(receiptService.getByUserId(userId));
    }
}
