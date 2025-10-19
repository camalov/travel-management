package az.ingress.mapper;

import az.ingress.dao.entity.DestinationEntity;
import az.ingress.model.request.DestinationRequest;
import az.ingress.model.response.DestinationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DestinationMapper {
    DestinationResponse toResponse(DestinationEntity destinationEntity);

    DestinationEntity toEntity(DestinationRequest request);
}
