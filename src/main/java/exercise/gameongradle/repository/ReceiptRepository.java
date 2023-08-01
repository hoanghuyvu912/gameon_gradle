package exercise.gameongradle.repository;

import com.nonIt.GameOn.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
//    @Query(value = "SELECT r from Receipt r join User u on r.user.id = u.id where u.id = :id")
//    List<Receipt> getReceiptByUserId(@Param("id") Integer userId);
//
//    @Query("SELECT r FROM Receipt r WHERE r.receiptDate > ?1 AND r.receiptDate < ?2")
//    List<Receipt> findReceiptsBetweenDates(LocalDate date1, LocalDate date2);

    List<Receipt> findByUserId(Integer userId);

    List<Receipt> findByReceiptDateBetween(LocalDate date1, LocalDate date2);

    List<Receipt> findByReceiptDateAfter(LocalDate date);

    List<Receipt> findByReceiptDateBefore(LocalDate date);
}
