package exercise.gameongradle.service.customDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameLibraryDto {
    private Integer id;
    private String name;
    private String thumbnail;
    private LocalDate receiptDate;
    private Double price;
    private String gameCode;
}
