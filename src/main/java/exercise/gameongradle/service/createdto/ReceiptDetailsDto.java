package exercise.gameongradle.service.createdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDetailsDto {
    private Integer receiptId;

    private Integer gameCodeId;
    private Double gamePrice;
}
