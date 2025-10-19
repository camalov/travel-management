package az.ingress.service.abstraction;

import az.ingress.dao.entity.TourEntity;
import az.ingress.model.request.TourRequest;
import az.ingress.model.response.TourResponse;

import java.util.Set;

public interface TourService {

    TourResponse createTour(TourRequest request);
    TourResponse findTourById(Long id);
    TourEntity findEntityById(Long id);
    Set<TourResponse> findToursByIds(Set<Long> ids);
    TourResponse updateTour(Long id, TourRequest request);
    void deleteTour(Long id);

    void addTravelerToTour(Long tourId, Long travelerId);
    Set<Long> getTravelerIdsForTour(Long tourId);
    Set<TourResponse> findToursByTraveler(Long travelerId);

    void assignGuideToTour(Long tourId, Long guideId);
    Set<Long> getGuideIdsForTour(Long tourId);
    Set<TourResponse> findToursByGuide(Long guideId);
}
