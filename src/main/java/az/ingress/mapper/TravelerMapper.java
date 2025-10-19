package az.ingress.mapper;

import az.ingress.dao.entity.TravelerEntity;
import az.ingress.model.request.TravelerRequest;
import az.ingress.model.response.TravelerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TourMapper.class)
public interface TravelerMapper {
    TravelerResponse toResponse(TravelerEntity travelerEntity);

    TravelerEntity toEntity(TravelerRequest request);
}
