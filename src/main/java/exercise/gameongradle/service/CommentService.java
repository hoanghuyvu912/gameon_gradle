package exercise.gameongradle.service;

import exercise.gameongradle.rest.resourcesdto.SimplifiedCommentDto;
import exercise.gameongradle.service.createdto.CommentDto;
import exercise.gameongradle.service.restdto.CommentRestDto;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {
    List<CommentRestDto> getAll();

    CommentRestDto findById(Integer commentId);

    CommentRestDto createComment(CommentDto commentDto);

    CommentRestDto updateComment(Integer commentId, CommentDto commentDto);

    void deleteComment(Integer commentId, String authorization, List<String> roles);

    List<SimplifiedCommentDto> getByUserId(Integer userId);

    List<CommentRestDto> getByUsername(String username);

    List<SimplifiedCommentDto> getByGameId(Integer gameId);

    List<CommentRestDto> getByGameName(String gameName);

    List<CommentRestDto> findByCommentDateAfter(LocalDate date);

    List<CommentRestDto> findByCommentDateBefore(LocalDate date);
}
