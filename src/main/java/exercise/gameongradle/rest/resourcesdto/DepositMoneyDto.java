package exercise.gameongradle.rest.resourcesdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepositMoneyDto {
    private Integer userId;
    private Double amount;
}
