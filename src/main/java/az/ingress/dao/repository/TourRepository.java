package az.ingress.dao.repository;

import az.ingress.dao.entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface TourRepository extends JpaRepository<TourEntity, Long> {

    @Query("SELECT t FROM TourEntity t JOIN t.guides g WHERE g.id = :guideId AND t.id != :tourId AND " +
            "(:newStartDate < t.endDate) AND (:newEndDate > t.startDate)")
    List<TourEntity> findConflictingToursForGuide(@Param("guideId") Long guideId,
                                                  @Param("tourId") Long tourId,
                                                  @Param("newStartDate") LocalDate newStartDate,
                                                  @Param("newEndDate") LocalDate newEndDate);

    Set<TourEntity> findByTravelers_Id(Long travelerId);

    Set<TourEntity> findByGuides_Id(Long guideId);

}
