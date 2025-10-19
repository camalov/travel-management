package az.ingress.dao.repository;

import az.ingress.dao.entity.DestinationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<DestinationEntity, Long> {
}
