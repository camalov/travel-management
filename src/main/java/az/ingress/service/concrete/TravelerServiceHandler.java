package az.ingress.service.concrete;

import az.ingress.dao.entity.TravelerEntity;
import az.ingress.dao.repository.TravelerRepository;
import az.ingress.mapper.TravelerMapper;
import az.ingress.model.request.TravelerRequest;
import az.ingress.model.response.TravelerResponse;
import az.ingress.service.abstraction.TravelerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelerServiceHandler implements TravelerService {

    private final TravelerRepository travelerRepository;
    private final TravelerMapper travelerMapper;

    @Override
    public TravelerResponse createTraveler(TravelerRequest request) {
        TravelerEntity entity = travelerMapper.toEntity(request);
        return travelerMapper.toResponse(travelerRepository.save(entity));
    }

    @Override
    public TravelerResponse findById(Long id) {
        return travelerRepository.findById(id)
                .map(travelerMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Səyyah tapılmadı: " + id));
    }

    @Override
    public TravelerEntity findEntityById(Long id) {
        return travelerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Səyyah tapılmadı: " + id));
    }

    @Override
    public Set<TravelerResponse> findByIds(Set<Long> ids) {
        return travelerRepository.findAllById(ids).stream()
                .map(travelerMapper::toResponse)
                .collect(Collectors.toSet());
    }
}
