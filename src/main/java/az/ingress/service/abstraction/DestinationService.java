package az.ingress.service.abstraction;

import az.ingress.model.request.DestinationRequest;
import az.ingress.model.response.DestinationResponse;

import java.util.List;

public interface DestinationService {
    DestinationResponse addDestinationToTour(Long tourId, DestinationRequest request);

    List<DestinationResponse> getDestinationsForTour(Long tourId);
}
