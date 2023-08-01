package exercise.gameongradle.service.customDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameSearchDto {
    private String name;

    private LocalDate releasedDateAfter;

    private LocalDate releasedDateBefore;

    private String systemReq;

    private Double priceLessThan;

    private Double priceGreaterThan;

    private Integer developerId;

    private Integer publisherId;

    private String developerName;

    private String publisherName;
}
