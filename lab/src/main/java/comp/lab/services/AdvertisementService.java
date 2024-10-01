package comp.lab.services;

import comp.lab.dto.AdvertisementDto;
import comp.lab.exceptions.UserNotFoundException;
import comp.lab.entity.*;
import comp.lab.repositories.AdvertisementRepository;
import comp.lab.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;

    private final UserService userService;
    private final RegionService regionService;
    private final SectionService sectionService;
    private final CityService cityService;

    @Autowired
    public AdvertisementService(
            AdvertisementRepository advertisementRepository,
            UserService userService,
            RegionService regionService,
            SectionService sectionService,
            CityService cityService
    ) {
        this.advertisementRepository = advertisementRepository;
        this.userService = userService;
        this.regionService = regionService;
        this.sectionService = sectionService;
        this.cityService = cityService;
    }

    public List<AdvertisementEntity> getAdvertisements() {
        return advertisementRepository.findAll();
    }

    public AdvertisementEntity findAdvertisementById(Long advertisementId) {
        return advertisementRepository.findById(advertisementId).orElseThrow(
                () -> new IllegalArgumentException("add with id " + advertisementId + " does not exists.")
        );
    }

    public List<AdvertisementEntity> findAdvertisementsByRegionName(String regionName) {
        return advertisementRepository.findAdvertisementsByRegionName(regionName);
    }

    public List<AdvertisementEntity> findAdvertisementsBySectionName(String sectionName) {
        return advertisementRepository.findAdvertisementsBySectionName(sectionName);
    }

    @Transactional
    public void addNewAdvertisementWithEmail(
            String userEmail,
            AdvertisementDto advertisementDto
    ) {
        UserEntity userEntity = userService.findUserByEmail(userEmail).orElseThrow(
                () -> new UserNotFoundException("user with email " + userEmail + " does not exists.")
        );

        String regionName  = advertisementDto.getRegionName();
        RegionEntity regionEntity = regionService.findRegionByName(regionName).orElseThrow(
                () -> new IllegalArgumentException("region with name " + regionName + " does not exists.")
        );

        String cityName = advertisementDto.getCityName();
        CityEntity cityEntity = cityService.findCityByName(cityName).orElseThrow(
                () -> new IllegalArgumentException("city with name " + cityName + " does not exists.")
        );

        String sectionName = advertisementDto.getSectionName();
        SectionEntity sectionEntity = sectionService.findSectionByName(sectionName).orElseThrow(
                () -> new IllegalArgumentException("section with name " + sectionName + " does not exists.")
        );

        String name = advertisementDto.getName();
        Integer price = advertisementDto.getPrice();
        String description = advertisementDto.getDescription();
        AdvertisementEntity advertisementEntity = new AdvertisementEntity(
                userEntity,
                name,
                price,
                description,
                regionEntity,
                cityEntity,
                sectionEntity
        );

        userEntity.getAdvertisementEntities().add(advertisementEntity);
        advertisementEntity.setUser(userEntity);
        userService.addUser(userEntity);
    }
}
