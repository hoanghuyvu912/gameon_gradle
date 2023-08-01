package exercise.gameongradle.rest.resourcesdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedReceiptDetailsDto {
    private Integer receiptDetailsId;
    private Integer gameCodeId;
    private Integer receiptId;
}
