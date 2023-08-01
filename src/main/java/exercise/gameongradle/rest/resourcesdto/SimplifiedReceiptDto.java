package exercise.gameongradle.rest.resourcesdto;

import com.nonIt.GameOn.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedReceiptDto {
    private Integer id;

    private User userId;

    private String username;

    private LocalDate receiptDate;
}
