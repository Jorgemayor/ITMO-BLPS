package comp.lab.repositories;

import comp.lab.entity.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<AdvertisementEntity, Long>, JpaSpecificationExecutor<AdvertisementEntity> {
    List<AdvertisementEntity> findAdvertisementsByRegionName(String name);
    List<AdvertisementEntity> findAdvertisementsBySectionName(String name);
}
