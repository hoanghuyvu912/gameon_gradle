package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.Genre;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.GenreRepository;
import com.nonIt.GameOn.service.GenreService;
import com.nonIt.GameOn.service.createdto.GenreDto;
import com.nonIt.GameOn.service.mapper.GenreMapper;
import com.nonIt.GameOn.service.restdto.GenreRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Override
    public List<GenreRestDto> getAll() {
        return genreRepository.findAll().stream().map(genreMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public GenreRestDto findById(Integer genreId) {
        return genreRepository.findById(genreId).map(genreMapper::toDto).orElseThrow(GameOnException::GenreNotFound);
    }

    @Override
    public GenreRestDto createGenre(GenreDto genreDto) {
        if (genreDto.getName() == null || genreDto.getName().trim().isBlank() || genreDto.getName().isEmpty()) {
            throw GameOnException.badRequest("GenreNameNotFound", "Genre name is missing");
        }
        Genre genre = Genre.builder()
                .name(genreDto.getName())
                .build();
        genre = genreRepository.save(genre);
        return genreMapper.toDto(genre);
    }

    @Override
    public GenreRestDto updateGenre(Integer genreId, GenreDto genreDto) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(GameOnException::GenreNotFound);

        if (genreDto.getName() == null || genreDto.getName().trim().isBlank() || genreDto.getName().isEmpty()) {
            throw GameOnException.badRequest("GenreNameNotFound", "Genre name is missing");
        }

        genre.setName(genreDto.getName());

        return genreMapper.toDto(genreRepository.save(genre));
    }

    @Override
    public void deleteGenre(Integer genreId) {
        genreRepository.deleteById(genreId);
    }
}
