package az.ingress.service.concrete;

import az.ingress.dao.entity.GuideEntity;
import az.ingress.dao.entity.PassportEntity;
import az.ingress.dao.repository.PassportRepository;
import az.ingress.mapper.PassportMapper;
import az.ingress.model.request.PassportRequest;
import az.ingress.model.response.PassportResponse;
import az.ingress.service.abstraction.GuideService;
import az.ingress.service.abstraction.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassportServiceHandler implements PassportService {

    private final PassportRepository passportRepository;
    private final PassportMapper passportMapper;
    private final GuideService guideService;

    @Override
    @Transactional
    public PassportResponse addOrUpdatePassport(Long guideId, PassportRequest request) {
        GuideEntity guide = guideService.findEntityById(guideId);

        PassportEntity passport = Optional.ofNullable(guide.getPassport())
                .map(existingPassport -> {
                    passportMapper.updateEntity(existingPassport, request);
                    return existingPassport;
                })
                .orElseGet(() -> {
                    PassportEntity newPassport = passportMapper.toEntity(request);
                    newPassport.setGuide(guide);
                    guide.setPassport(newPassport);
                    return newPassport;
                });

        return passportMapper.toResponse(passportRepository.save(passport));
    }
}
