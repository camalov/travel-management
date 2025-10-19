package az.ingress.dao.repository;

import az.ingress.dao.entity.GuideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface GuideRepository extends JpaRepository<GuideEntity, Long> {

    @Query("""
            SELECT g FROM GuideEntity g WHERE g.id NOT IN (
                SELECT gu.id FROM TourEntity t JOIN t.guides gu WHERE
                (:startDate < t.endDate) AND (:endDate > t.startDate)
            )
            """)
    List<GuideEntity> findAvailableGuidesInPeriod(LocalDate startDate, LocalDate endDate);

}
