package az.ingress.service.concrete;

import az.ingress.dao.entity.DestinationEntity;
import az.ingress.dao.entity.TourEntity;
import az.ingress.dao.repository.DestinationRepository;
import az.ingress.mapper.DestinationMapper;
import az.ingress.model.request.DestinationRequest;
import az.ingress.model.response.DestinationResponse;
import az.ingress.service.abstraction.DestinationService;
import az.ingress.service.abstraction.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DestinationServiceHandler implements DestinationService {

    private final DestinationRepository destinationRepository;
    private final DestinationMapper destinationMapper;
    private final TourService tourService;

    @Override
    public DestinationResponse addDestinationToTour(Long tourId, DestinationRequest request) {
        TourEntity tour = tourService.findEntityById(tourId);
        DestinationEntity destination = destinationMapper.toEntity(request);
        destination.setTour(tour);
        return destinationMapper.toResponse(destinationRepository.save(destination));
    }

    @Override
    public List<DestinationResponse> getDestinationsForTour(Long tourId) {
        TourEntity tour = tourService.findEntityById(tourId);
        return tour.getDestinations().stream()
                .map(destinationMapper::toResponse)
                .collect(Collectors.toList());
    }
}
