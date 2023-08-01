package exercise.gameongradle.service.customDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptResponseDto {
    private Integer id;
    private Integer userId;
    private String userName;

    private LocalDate receiptDate;
}
