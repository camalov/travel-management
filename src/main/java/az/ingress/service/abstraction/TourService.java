package az.ingress.service.abstraction;

import az.ingress.dao.entity.Destination;
import az.ingress.dao.entity.Guide;
import az.ingress.dao.entity.Tour;
import az.ingress.dao.entity.Traveler;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface TourService {

    Tour assignGuideToTour(Long tourId, Long guideId);

    Tour addTravelerToTour(Long tourId, Long travelerId);

    List<Destination> getDestinationsForTour(Long tourId);

    Set<Traveler> getTravelersForTour(Long tourId);

    List<Optional<Guide>> getGuidesWithPassportsForTour(Long tourId);
}
