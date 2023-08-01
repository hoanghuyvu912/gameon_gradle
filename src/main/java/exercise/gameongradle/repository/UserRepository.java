package exercise.gameongradle.repository;

import com.nonIt.GameOn.entity.Gender;
import com.nonIt.GameOn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    List<User> findByGender(@Param("string") Gender gender);

    List<User> findByFirstNameContaining(String name);

    List<User> findByLastNameContaining(String name);

    List<User> findByEmailContaining(String name);

    List<User> findByTelContaining(String name);

    List<User> findByAddressContaining(String name);

    List<User> findByDobAfter(LocalDate date);

    List<User> findByDobBefore(LocalDate date);

    List<User> findByBalanceGreaterThan(Double balance);

    List<User> findByBalanceLessThan(Double balance);

    List<User> findByActiveTrue();

    List<User> findByActiveFalse();
}
