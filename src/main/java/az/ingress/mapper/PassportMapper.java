package az.ingress.mapper;

import az.ingress.dao.entity.PassportEntity;
import az.ingress.model.request.PassportRequest;
import az.ingress.model.response.PassportResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PassportMapper {
    PassportResponse toResponse(PassportEntity passportEntity);

    PassportEntity toEntity(PassportRequest request);

    void updateEntity(@MappingTarget PassportEntity passportEntity, PassportRequest request);
}
