package in.javondaniels.moneymanager.repository;

import in.javondaniels.moneymanager.entity.ExpenseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    //select * from tbl_expenses where profile_id =?1 order by date desc
    List<ExpenseEntity> findByProfile_IdOrderByDateDesc(Long profileId);

    //select * from tbl_expenses where profile_id = ?1 order by date desc limit 5
    List<ExpenseEntity> findTop5ByProfile_IdOrderByDateDesc(Long profileId);

    Optional<ExpenseEntity> findByIdAndProfile_Id(Long id, Long profileId);


    @Query("SELECT SUM(e.amount) FROM ExpenseEntity e WHERE e.profile.id = :profileId")
    BigDecimal findTotalExpenseByProfile_Id (@Param("profileId") Long profileId);

    List<ExpenseEntity>findByProfile_IdAndDateBetweenAndNameContainingIgnoreCase(
            Long profileId,
            LocalDate startDate,
            LocalDate endDate,
            String keyword,
            Sort sort
    );

    List<ExpenseEntity>findByProfile_IdAndDateBetween(Long ProfileId, LocalDate startDate, LocalDate endDate);

    List<ExpenseEntity> findTop5ByProfileIdOrderByDateDesc(Long id);

    List<ExpenseEntity> findByProfileIdAndDate(Long profileId, LocalDate date);
}
