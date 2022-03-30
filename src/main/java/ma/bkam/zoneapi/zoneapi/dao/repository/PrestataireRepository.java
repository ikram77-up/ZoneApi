package ma.bkam.zoneapi.zoneapi.dao.repository;

import ma.bkam.zoneapi.zoneapi.dao.model.PrestataireEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrestataireRepository extends JpaRepository<PrestataireEntity, Long> {
}
