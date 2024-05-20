package comp.lab.config;

import comp.lab.model.*;
import comp.lab.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            CityRepository cityRepository,
            RegionRepository regionRepository,
            SectionRepository sectionRepository,
            AdvertisementRepository advertisementRepository
    ) {
        return args -> {
            User admin = new User(
                    "admin",
                    "admin@mail.com",
                    "$2a$12$ZrrZJzkY4KEprzymVnIiAOlntmZt0BmGFACO4MV7lNtNPTR1lpC4y",
                    Role.ADMIN,
                    LocalDate.of(2000, Month.MAY, 19)
            );

            User user1 = new User(
                    "user1",
                    "user1@mail.com",
                    "$2a$12$X9LVo8lu15aCeGAKzXlJBu6Wb7GAduPcI8uODla7bwGANKz2wrlqy",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            User user2 = new User(
                    "user2",
                    "user2@mail.com",
                    "$2a$12$X9LVo8lu15aCeGAKzXlJBu6Wb7GAduPcI8uODla7bwGANKz2wrlqy",
                    LocalDate.of(2000, Month.JUNE, 5)
            );

            userRepository.saveAll(
                    List.of(admin, user1, user2)
            );

            Region region3 = new Region(
                    "region3"
            );

            Region region4 = new Region(
                    "region4"
            );

            regionRepository.saveAll(
                    List.of(region3, region4)
            );

            City city1 = new City(
                    "city1",
                    region3
            );

            City city2 = new City(
                    "city2",
                    region4
            );

            cityRepository.saveAll(
                    List.of(city1, city2)
            );

            Section aDefault = new Section(
                    "default"
            );

            Section section1 = new Section(
                    "section1"
            );

            sectionRepository.saveAll(
                    List.of(aDefault, section1)
            );

            Advertisement add1 = new Advertisement(
                    user1,
                    "add1",
                    1,
                    "desc1",
                    region3,
                    city1,
                    aDefault
            );

            Advertisement add2 = new Advertisement(
                    user2,
                    "add2",
                    2,
                    "desc2",
                    region4,
                    city2,
                    section1
            );

            advertisementRepository.saveAll(
                    List.of(add1, add2)
            );
        };
    }
}
