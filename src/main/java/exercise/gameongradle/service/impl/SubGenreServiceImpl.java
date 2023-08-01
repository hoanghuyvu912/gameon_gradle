package exercise.gameongradle.service.impl;

import com.nonIt.GameOn.entity.SubGenre;
import com.nonIt.GameOn.exception.GameOnException;
import com.nonIt.GameOn.repository.SubGenreRepository;
import com.nonIt.GameOn.service.SubGenreService;
import com.nonIt.GameOn.service.createdto.SubGenreDto;
import com.nonIt.GameOn.service.mapper.SubGenreMapper;
import com.nonIt.GameOn.service.restdto.SubGenreRestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SubGenreServiceImpl implements SubGenreService {
    private final SubGenreRepository subGenreRepository;

    private final SubGenreMapper subGenreMapper;

    @Override
    public List<SubGenreRestDto> getAll() {
        return subGenreRepository.findAll().stream().map(subGenreMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public SubGenreRestDto findById(Integer subGenreId) {
        return subGenreRepository.findById(subGenreId).map(subGenreMapper::toDto).orElseThrow(GameOnException::SubGenreNotFound);
    }

    @Override
    public SubGenreRestDto createSubGenre(SubGenreDto SubGenreDto) {
        if (SubGenreDto.getName() == null || SubGenreDto.getName().trim().isBlank() || SubGenreDto.getName().isEmpty()) {
            throw GameOnException.badRequest("SubGenreNameNotFound", "SubGenre name is missing");
        }
        SubGenre subGenre = SubGenre.builder()
                .name(SubGenreDto.getName())
                .build();
        subGenre = subGenreRepository.save(subGenre);
        return subGenreMapper.toDto(subGenre);
    }

    @Override
    public SubGenreRestDto updateSubGenre(Integer subGenreId, SubGenreDto subGenreDto) {
        SubGenre subGenre = subGenreRepository.findById(subGenreId).orElseThrow(GameOnException::SubGenreNotFound);

        if (subGenreDto.getName() == null || subGenreDto.getName().trim().isBlank() || subGenreDto.getName().isEmpty()) {
            throw GameOnException.badRequest("SubGenreNameNotFound", "SubGenre name is missing");
        }

        subGenre.setName(subGenreDto.getName());

        return subGenreMapper.toDto(subGenreRepository.save(subGenre));
    }

    @Override
    public void deleteSubGenre(Integer subGenreId) {
        subGenreRepository.deleteById(subGenreId);
    }
}
