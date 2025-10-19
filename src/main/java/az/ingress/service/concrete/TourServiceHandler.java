package az.ingress.service.concrete;

import az.ingress.dao.entity.GuideEntity;
import az.ingress.dao.entity.TourEntity;
import az.ingress.dao.entity.TravelerEntity;
import az.ingress.dao.repository.TourRepository;
import az.ingress.mapper.TourMapper;
import az.ingress.model.request.TourRequest;
import az.ingress.model.response.TourResponse;
import az.ingress.service.abstraction.GuideService;
import az.ingress.service.abstraction.TourService;
import az.ingress.service.abstraction.TravelerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourServiceHandler implements TourService {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    private final TravelerService travelerService;
    private final GuideService guideService;

    @Override
    public TourResponse createTour(TourRequest request) {
        TourEntity tourEntity = tourMapper.toEntity(request);
        return tourMapper.toResponse(tourRepository.save(tourEntity));
    }

    @Override
    public TourResponse findTourById(Long id) {
        return tourRepository.findById(id)
                .map(tourMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found: " + id));
    }

    @Override
    public TourEntity findEntityById(Long id) {
        return tourRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tour not found: " + id));
    }

    @Override
    public Set<TourResponse> findToursByIds(Set<Long> ids) {
        return tourRepository.findAllById(ids).stream()
                .map(tourMapper::toResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public TourResponse updateTour(Long id, TourRequest request) {
        return tourRepository.findById(id)
                .map(tourEntity -> {
                    tourMapper.updateEntity(tourEntity, request);
                    return tourMapper.toResponse(tourRepository.save(tourEntity));
                })
                .orElseThrow(() -> new IllegalArgumentException("Tour not found: " + id));
    }

    @Override
    public void deleteTour(Long id) {
        if (!tourRepository.existsById(id)) {
            throw new IllegalArgumentException("Tour not found: " + id);
        }
        tourRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addTravelerToTour(Long tourId, Long travelerId) {
        TourEntity tour = findEntityById(tourId);
        TravelerEntity traveler = travelerService.findEntityById(travelerId);
        tour.getTravelers().add(traveler);
        tourRepository.save(tour);
    }

    @Override
    public Set<Long> getTravelerIdsForTour(Long tourId) {
        TourEntity tour = findEntityById(tourId);
        return tour.getTravelers().stream()
                .map(TravelerEntity::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TourResponse> findToursByTraveler(Long travelerId) {
        return tourRepository.findByTravelers_Id(travelerId).stream()
                .map(tourMapper::toResponse)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public void assignGuideToTour(Long tourId, Long guideId) {
        TourEntity tour = findEntityById(tourId);
        GuideEntity guide = guideService.findEntityById(guideId);

        var conflictingTours = tourRepository.findConflictingToursForGuide(guideId, tourId, tour.getStartDate(), tour.getEndDate());
        if (!conflictingTours.isEmpty()) {
            throw new IllegalStateException("Guide is already assigned to another tour in the given dates.");
        }

        tour.getGuides().add(guide);
        tourRepository.save(tour);
    }

    @Override
    public Set<Long> getGuideIdsForTour(Long tourId) {
        TourEntity tour = findEntityById(tourId);
        return tour.getGuides().stream()
                .map(GuideEntity::getId)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TourResponse> findToursByGuide(Long guideId) {
        return tourRepository.findByGuides_Id(guideId).stream()
                .map(tourMapper::toResponse)
                .collect(Collectors.toSet());
    }
}
