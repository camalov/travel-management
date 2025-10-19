package az.ingress.service.abstraction;

import az.ingress.dao.entity.GuideEntity;
import az.ingress.model.request.GuideRequest;
import az.ingress.model.response.GuideResponse;

import java.util.List;
import java.util.Set;

public interface GuideService {
    GuideResponse createGuide(GuideRequest request);

    GuideResponse findById(Long id);

    GuideEntity findEntityById(Long id);

    Set<GuideResponse> findByIds(Set<Long> ids);

    List<GuideResponse> findAll();
}
