package exercise.gameongradle.repository;

import exercise.gameongradle.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value = "SELECT c from Comment c join User u on c.user.id = u.id where u.id = :id")
    List<Comment> getCommentByUserId(@Param("id") Integer userId);

    @Query(value = "SELECT c from Comment c join User u on c.user.id = u.id where UPPER(u.username) like UPPER(:username)")
    List<Comment> getCommentByUsername(@Param("username") String username);

    @Query(value = "SELECT c from Comment c join Game g on c.game.id = g.id where g.id = :id")
    List<Comment> getCommentByGameId(@Param("id") Integer userId);

    @Query(value = "SELECT c from Comment c join Game g on c.game.id = g.id where UPPER(g.name) like UPPER(:gameName)")
    List<Comment> getCommentByGameName(@Param("gameName") String gameName);

    List<Comment> findByCommentDateAfter(LocalDate date);

    List<Comment> findByCommentDateBefore(LocalDate date);
}
