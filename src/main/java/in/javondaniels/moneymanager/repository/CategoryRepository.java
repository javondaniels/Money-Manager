package in.javondaniels.moneymanager.repository;

import in.javondaniels.moneymanager.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    //select * from tbl_categories where profile_id = ?1
    List<CategoryEntity> findByProfile_Id(Long profileId);

    //select * from tbl_categories where id = ?1 and profile_Id = ?2
    Optional<CategoryEntity> findByIdAndProfile_Id(Long id, Long profileId);

    //select * from tbl_categories where type = ?1 and profile_Id = ?2
    List<CategoryEntity> findByTypeAndProfile_Id(String type, Long profileId);

    Boolean existsByNameAndProfile_Id(String name, Long profileId);
}
