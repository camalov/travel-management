package az.ingress.service.concrete;

import az.ingress.dao.entity.GuideEntity;
import az.ingress.dao.repository.GuideRepository;
import az.ingress.mapper.GuideMapper;
import az.ingress.model.request.GuideRequest;
import az.ingress.model.response.GuideResponse;
import az.ingress.service.abstraction.GuideService;
import az.ingress.service.abstraction.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class GuideServiceHandler implements GuideService {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;
    private final PassportService passportService;

    @Override
    public GuideResponse createGuide(GuideRequest request) {
        GuideEntity entity = guideMapper.toEntity(request);
        return guideMapper.toResponse(guideRepository.save(entity));
    }

    @Override
    public GuideResponse findById(Long id) {
        return guideRepository.findById(id)
                .map(guideMapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Guide not found: " + id));
    }

    @Override
    public GuideEntity findEntityById(Long id) {
        return guideRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Guide not found: " + id));
    }

    @Override
    public Set<GuideResponse> findByIds(Set<Long> ids) {
        return StreamSupport.stream(guideRepository.findAllById(ids).spliterator(), false)
                .map(guideMapper::toResponse)
                .collect(Collectors.toSet());
    }

    @Override
    public List<GuideResponse> findAll() {
        return StreamSupport.stream(guideRepository.findAll().spliterator(), false)
                .map(guideMapper::toResponse)
                .collect(Collectors.toList());
    }
}
