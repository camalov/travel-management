package az.ingress.dao.repository;

import az.ingress.dao.entity.Traveler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelerRepository extends CrudRepository<Traveler, Long> {
}
