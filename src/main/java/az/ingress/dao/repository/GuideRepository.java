package az.ingress.dao.repository;

import az.ingress.dao.entity.Guide;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GuideRepository extends CrudRepository<Guide, Long> {

    @Query("SELECT g FROM Guide g WHERE g.id NOT IN (" +
            "SELECT gu.id FROM Tour t JOIN t.guides gu WHERE " +
            "(:startDate < t.endDate) AND (:endDate > t.startDate))")
    List<Guide> findAvailableGuidesInPeriod(@Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate);

}
