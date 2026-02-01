package in.javondaniels.moneymanager.repository;

import in.javondaniels.moneymanager.entity.ExpenseEntity;
import in.javondaniels.moneymanager.entity.IncomeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository <IncomeEntity, Long> {
    //select * from tbl_incomes where profile_id =?1 order by date desc
    List<IncomeEntity> findByProfile_IdOrderByDateDesc(Long profileId);

    //select * from tbl_incomes where profile_id = ?1 order by date desc limit 5
    List<IncomeEntity> findTop5ByProfile_IdOrderByDateDesc(Long profileId);

    @Query("SELECT SUM(i.amount) FROM IncomeEntity i WHERE i.profile.id = :profileId")
    BigDecimal findTotalExpenseByProfile_Id (@Param("profileId") Long profileId);

    List<IncomeEntity>findByProfile_IdAndDateBetweenAndNameContainingIgnoreCase(
            Long profileId,
            LocalDate startDate,
            LocalDate endDate,
            String keyword,
            Sort sort
    );

    List<IncomeEntity>findByProfile_IdAndDateBetween(Long ProfileId, LocalDate startDate, LocalDate endDate);

    List<IncomeEntity> findTop5ByProfileIdOrderByDateDesc(Long id);
}
