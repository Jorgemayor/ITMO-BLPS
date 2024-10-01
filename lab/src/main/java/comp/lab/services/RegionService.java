package comp.lab.services;

import comp.lab.entity.RegionEntity;
import comp.lab.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Optional<RegionEntity> findRegionByName(String name) {
        return regionRepository.findByName(name);
    }
}
