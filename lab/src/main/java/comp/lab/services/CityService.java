package comp.lab.services;

import comp.lab.entity.CityEntity;
import comp.lab.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Optional<CityEntity> findCityByName(String name) {
        return cityRepository.findByName(name);
    }
}
