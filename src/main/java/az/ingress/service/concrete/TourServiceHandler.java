package az.ingress.service.concrete;

import az.ingress.dao.entity.Destination;
import az.ingress.dao.entity.Guide;
import az.ingress.dao.entity.Tour;
import az.ingress.dao.entity.Traveler;
import az.ingress.dao.repository.GuideRepository;
import az.ingress.dao.repository.TourRepository;
import az.ingress.dao.repository.TravelerRepository;
import az.ingress.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourServiceHandler implements TourService {

    private final TourRepository tourRepository;

    private final GuideRepository guideRepository;

    private final TravelerRepository travelerRepository;


    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Tour assignGuideToTour(Long tourId, Long guideId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour tapılmadı: " + tourId));
        Guide guide = guideRepository.findById(guideId)
                .orElseThrow(() -> new IllegalArgumentException("Guide tapılmadı: " + guideId));

        List<Tour> conflictingTours = tourRepository.findConflictingToursForGuide(
                guideId,
                tour.getStartDate(),
                tour.getEndDate()
        );

        if (!conflictingTours.isEmpty()) {
            String conflictNames = conflictingTours.stream()
                    .map(Tour::getName)
                    .collect(Collectors.joining(", "));

            throw new IllegalStateException(
                    String.format("Bələdçi (%s) bu tarixdə (%s - %s) artıq turlarda (%s) iştirak edir. " +
                                    "Təyinat ləğv edildi.",
                            guide.getName(), tour.getStartDate(), tour.getEndDate(), conflictNames));
        }

        tour.getGuides().add(guide);
        guide.getTours().add(tour);

        return tourRepository.save(tour);
    }

    @Override
    @Transactional
    public Tour addTravelerToTour(Long tourId, Long travelerId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour tapılmadı: " + tourId));
        Traveler traveler = travelerRepository.findById(travelerId)
                .orElseThrow(() -> new IllegalArgumentException("Səyyah tapılmadı: " + travelerId));

        tour.getTravelers().add(traveler);
        return tourRepository.save(tour);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Destination> getDestinationsForTour(Long tourId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour tapılmadı: " + tourId));
        return tour.getDestinations();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Traveler> getTravelersForTour(Long tourId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour tapılmadı: " + tourId));
        return tour.getTravelers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Optional<Guide>> getGuidesWithPassportsForTour(Long tourId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new IllegalArgumentException("Tour tapılmadı: " + tourId));

        return tour.getGuides().stream()
                .map(guide -> guideRepository.findById(guide.getId()))
                .collect(Collectors.toList());
    }
}
