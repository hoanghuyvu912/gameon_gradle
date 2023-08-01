package exercise.gameongradle.repository;

import com.nonIt.GameOn.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query(value = "SELECT r from Rating r join User u on r.user.id = u.id where u.id = :id")
    List<Rating> getRatingByUserId(@Param("id") Integer userId);

//    @Query( value = "select g.id as \"game_id\", g.game_name as \"game_name\", ui.id as \"user_id\" ,r.rating, r.id\n" +
//            "from game g join rating r on r.game_id = g.id \n" +
//            "join user_info ui on ui.id = r.user_id \n" +
//            "group by g.id, r.rating, r.id, ui.id \n" +
//            "HAVING r.rating between 3 and 4\n" +
//            "and g.released_date between '2022-01-01' and '2023-01-01'", nativeQuery = true)
//    List<Rating> findTop1ByOrderByRatingDescByGroupByGame();
}
