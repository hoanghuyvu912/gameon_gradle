package exercise.gameongradle.service;

import exercise.gameongradle.service.createdto.GenreDto;
import exercise.gameongradle.service.restdto.GenreRestDto;

import java.util.List;

public interface GenreService {
    List<GenreRestDto> getAll();

    GenreRestDto findById(Integer genreId);
    GenreRestDto createGenre(GenreDto genreDto);
    GenreRestDto updateGenre(Integer genreId, GenreDto genreDto);
    void deleteGenre(Integer genreId);
}
