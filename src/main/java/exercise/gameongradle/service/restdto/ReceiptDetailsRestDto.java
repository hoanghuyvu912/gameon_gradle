package exercise.gameongradle.service.restdto;

import com.nonIt.GameOn.entity.GameCode;
import com.nonIt.GameOn.entity.Receipt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDetailsRestDto {
    private Integer id;

    private Receipt receipt;

    private GameCode gameCode;
    private Double gamePrice;
}
