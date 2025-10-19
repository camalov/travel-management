package az.ingress.service.abstraction;

import az.ingress.dao.entity.TravelerEntity;
import az.ingress.model.request.TravelerRequest;
import az.ingress.model.response.TravelerResponse;

import java.util.Set;

public interface TravelerService {
    TravelerResponse createTraveler(TravelerRequest request);

    TravelerResponse findById(Long id);

    TravelerEntity findEntityById(Long id);

    Set<TravelerResponse> findByIds(Set<Long> ids);
}
