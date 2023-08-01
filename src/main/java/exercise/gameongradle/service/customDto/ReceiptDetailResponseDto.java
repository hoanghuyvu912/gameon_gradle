package exercise.gameongradle.service.customDto;

import com.nonIt.GameOn.entity.GameCodeStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDetailResponseDto {
    private Integer receiptDetailsId;
    private Integer receiptId;
    private String gameName;
    private Double gamePrice;
    private Integer gameCodeId;
    private GameCodeStatus gameCodeStatus;

}
