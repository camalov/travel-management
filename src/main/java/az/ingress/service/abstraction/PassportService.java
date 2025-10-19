package az.ingress.service.abstraction;

import az.ingress.model.request.PassportRequest;
import az.ingress.model.response.PassportResponse;

public interface PassportService {
    PassportResponse addOrUpdatePassport(Long guideId, PassportRequest request);
}
