package exercise.gameongradle.service.mapper;

import exercise.gameongradle.entity.Comment;
import exercise.gameongradle.rest.resourcesdto.SimplifiedCommentDto;
import exercise.gameongradle.service.createdto.CommentDto;
import exercise.gameongradle.service.restdto.CommentRestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper  extends EntityMapper<CommentRestDto, Comment, CommentDto>{
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "gameId", source = "game.id")
    SimplifiedCommentDto toSimplifiedDto(Comment comment);
}
