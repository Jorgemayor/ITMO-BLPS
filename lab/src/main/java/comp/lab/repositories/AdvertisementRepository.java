package comp.lab.repositories;

import comp.lab.model.Advertisement;
import comp.lab.model.Region;
import comp.lab.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>, JpaSpecificationExecutor<Advertisement> {
    List<Advertisement> findAdvertisementsByRegionName(String name);
    List<Advertisement> findAdvertisementsBySectionName(String name);
}
