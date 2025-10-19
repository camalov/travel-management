package az.ingress.dao.repository;

import az.ingress.dao.entity.TravelerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelerRepository extends JpaRepository<TravelerEntity, Long> {
}
