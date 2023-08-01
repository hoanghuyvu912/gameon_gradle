package exercise.gameongradle.service;

import exercise.gameongradle.service.createdto.SubGenreDto;
import exercise.gameongradle.service.restdto.SubGenreRestDto;

import java.util.List;

public interface SubGenreService {
    List<SubGenreRestDto> getAll();

    SubGenreRestDto findById(Integer subGenreId);

    SubGenreRestDto createSubGenre(SubGenreDto subGenreDto);

    SubGenreRestDto updateSubGenre(Integer subGenreId, SubGenreDto subGenreDto);

    void deleteSubGenre(Integer subGenreId);
}
