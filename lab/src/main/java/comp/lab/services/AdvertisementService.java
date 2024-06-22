package comp.lab.services;

import comp.lab.exceptions.UserNotFoundException;
import comp.lab.model.*;
import comp.lab.repositories.AdvertisementRepository;
import comp.lab.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Advertisement> getAdvertisements() {
        return advertisementRepository.findAll();
    }

    public Advertisement findAdvertisementById(Long advertisementId) {
        return advertisementRepository.findById(advertisementId).orElseThrow(
                () -> new IllegalArgumentException("add with id " + advertisementId + " does not exists.")
        );
    }

    public List<Advertisement> findAdvertisementsByRegionName(String regionName) {
        return advertisementRepository.findAdvertisementsByRegionName(regionName);
    }

    public List<Advertisement> findAdvertisementsBySectionName(String sectionName) {
        return advertisementRepository.findAdvertisementsBySectionName(sectionName);
    }

    @Transactional
    public void addNewAdvertisementWithEmail(
            UserPrincipal principal,
            String name,
            Integer price,
            String description,
            String regionName,
            String cityName,
            String sectionName
    ) {
        String userEmail = principal.getEmail();
        User user = userService.findUserByEmail(userEmail).orElseThrow(
                () -> new UserNotFoundException("user with email " + userEmail + " does not exists.")
        );

        Region region = regionService.findRegionByName(regionName).orElseThrow(
                () -> new IllegalArgumentException("region with name " + regionName + " does not exists.")
        );

        City city = cityService.findCityByName(cityName).orElseThrow(
                () -> new IllegalArgumentException("city with name " + cityName + " does not exists.")
        );

        Section section = sectionService.findSectionByName(sectionName).orElseThrow(
                () -> new IllegalArgumentException("section with name " + sectionName + " does not exists.")
        );

        Advertisement advertisement = new Advertisement(
                user,
                name,
                price,
                description,
                region,
                city,
                section
        );

        user.getAdvertisements().add(advertisement);
        advertisement.setUser(user);
        userService.addUser(user);
    }
}
