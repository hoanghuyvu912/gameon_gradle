package exercise.gameongradle.repository;

import com.nonIt.GameOn.entity.ReceiptDetails;
import com.nonIt.GameOn.service.customDto.GameStatisticsDto;
import com.nonIt.GameOn.service.customDto.ReceiptDetailResponseDto;
import com.nonIt.GameOn.service.customDto.RevenuePerMonthInYearDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceiptDetailsRepository extends JpaRepository<ReceiptDetails, Integer> {
//    List<ReceiptDetails> findByReceiptReceiptDateBetween

//        @Query("SELECT new com.nonIt.GameOn.service.customDto.RevenuePerDateDto(r.receiptDate, count(g.id), sum(g.price)) " +
//            "FROM Game g join ReceiptDetails rd ON g.id = rd.game.id " +
//            "JOIN Receipt r on r.id = rd.receipt.id " +
//            "GROUP BY r.receiptDate " +
//            "HAVING r.receiptDate between :date1 and :date2")
//    List<RevenuePerMonthDto> getRevenuePerDateBetweenDates(@Param("date1")LocalDate date1, @Param("date2")LocalDate date2);

//    @Query("SELECT rd FROM ReceiptDetails rd JOIN rd.receipt r WHERE r.receiptDate >= ?1 AND r.receiptDate <= ?2")
//    List<ReceiptDetails> findByReceiptReceiptDate(LocalDate startDate, LocalDate endDate);

    List<ReceiptDetails> findByReceiptReceiptDateBetween(LocalDate startDate, LocalDate endDate);

    List<ReceiptDetails> findByReceiptUserId(@Param("userId") Integer userId);

    @Query(value = "SELECT new com.nonIt.GameOn.service.customDto.RevenuePerMonthInYearDto(sum(rd.gamePrice)) FROM Receipt r JOIN ReceiptDetails rd ON r.id = rd.receipt.id WHERE EXTRACT(MONTH FROM r.receiptDate) = ?1 AND EXTRACT(YEAR FROM r.receiptDate) = ?2 ")
    RevenuePerMonthInYearDto getRevenuePerMonthInYear(Integer month, Integer year);

    @Query(value="SELECT new com.nonIt.GameOn.service.customDto.GameStatisticsDto(gc.game,COUNT(gc.game.id),SUM(rd.gamePrice)) " +
            "FROM Receipt r " +
            "JOIN ReceiptDetails rd ON r.id = rd.receipt.id " +
            "JOIN GameCode gc ON gc.id = rd.gameCode.id " +
            "WHERE EXTRACT(MONTH FROM r.receiptDate) = ?1 " +
            "AND EXTRACT(YEAR FROM r.receiptDate) = ?2 " +
            "AND gc.id = rd.gameCode.id " +
            "GROUP BY gc.game.id")
    List<GameStatisticsDto> getGameStatisticsPerMonth(Integer month, Integer year);

    @Query("SELECT new com.nonIt.GameOn.service.customDto.ReceiptDetailResponseDto( rd.id,rd.receipt.id, g.name, rd.gamePrice, gc.id, gc.gameCodeStatus) " +
            "FROM ReceiptDetails rd " +
            "JOIN GameCode gc on gc.id = rd.gameCode.id " +
            "JOIN Game g on g.id = gc.game.id " +
            "WHERE rd.receipt.id = :receiptId")
    List<ReceiptDetailResponseDto> findByReceiptId(Integer receiptId);
}

