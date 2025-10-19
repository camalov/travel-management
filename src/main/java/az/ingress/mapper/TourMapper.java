package az.ingress.mapper;

import az.ingress.dao.entity.TourEntity;
import az.ingress.model.request.TourRequest;
import az.ingress.model.response.TourResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {DestinationMapper.class, GuideMapper.class, TravelerMapper.class})
public interface TourMapper {
    TourResponse toResponse(TourEntity tourEntity);

    TourEntity toEntity(TourRequest request);

    void updateEntity(@MappingTarget TourEntity tourEntity, TourRequest request);
}
