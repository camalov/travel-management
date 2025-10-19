package az.ingress.mapper;

import az.ingress.dao.entity.GuideEntity;
import az.ingress.model.request.GuideRequest;
import az.ingress.model.response.GuideResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PassportMapper.class)
public interface GuideMapper {
    GuideResponse toResponse(GuideEntity guideEntity);

    GuideEntity toEntity(GuideRequest request);
}
