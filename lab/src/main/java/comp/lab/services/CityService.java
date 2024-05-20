package comp.lab.services;

import comp.lab.model.City;
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

    public Optional<City> findCityByName(String name) {
        return cityRepository.findByName(name);
    }
}
