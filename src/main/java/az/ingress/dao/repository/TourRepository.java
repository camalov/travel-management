package az.ingress.dao.repository;

import az.ingress.dao.entity.Tour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourRepository extends CrudRepository<Tour, Long> {

    List<Tour> getAllByTravellerId(@Param("travelerId") Long id);

    @Query("SELECT t FROM Tour t JOIN t.guides g WHERE g.id = :guideId AND " +
            "(:newStartDate < t.endDate) AND (:newEndDate > t.startDate)")
    List<Tour> findConflictingToursForGuide(@Param("guideId") Long guideId,
                                            @Param("newStartDate") LocalDate newStartDate,
                                            @Param("newEndDate") LocalDate newEndDate);

}
