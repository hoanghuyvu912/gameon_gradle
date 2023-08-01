package exercise.gameongradle.rest.resourcesdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedGameDto {
    private Integer id;
    private String name;
    private String thumbnail;
    private String description;
    private String trailer;
    private LocalDate releasedDate;
    private String systemReq;
    private Double price;
    private Integer developerId;
    private String developerName;
    private Integer publisherId;
    private String publisherName;

    private List<SimplifiedCommentDto> simplifiedCommentDtoList = new ArrayList<>();
    private List<SimplifiedRatingDto> simplifiedRatingDtoList = new ArrayList<>();
    private List<SimplifiedGameImageDto> simplifiedGameImageDtoList = new ArrayList<>();
    private List<SimplifiedGameSubGenreDto> simplifiedGameSubGenreDtoList = new ArrayList<>();
    private List<SimplifiedGameGenreDto> simplifiedGameGenreDtoList = new ArrayList<>();
}
