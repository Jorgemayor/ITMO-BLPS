package comp.lab.config;

import comp.lab.entity.*;
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
            UserEntity admin = new UserEntity(
                    "admin",
                    "admin@mail.com",
                    "admin",
                    Role.ADMIN,
                    LocalDate.of(2000, Month.MAY, 19)
            );

            UserEntity user1 = new UserEntity(
                    "user1",
                    "user1@mail.com",
                    "user",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            UserEntity user2 = new UserEntity(
                    "user2",
                    "user2@mail.com",
                    "user",
                    LocalDate.of(2000, Month.JUNE, 5)
            );

            userRepository.saveAll(
                    List.of(admin, user1, user2)
            );

            RegionEntity region3 = new RegionEntity(
                    "region3"
            );

            RegionEntity region4 = new RegionEntity(
                    "region4"
            );

            regionRepository.saveAll(
                    List.of(region3, region4)
            );

            CityEntity city1 = new CityEntity(
                    "city1",
                    region3
            );

            CityEntity city2 = new CityEntity(
                    "city2",
                    region4
            );

            cityRepository.saveAll(
                    List.of(city1, city2)
            );

            SectionEntity aDefault = new SectionEntity(
                    "default"
            );

            SectionEntity section1 = new SectionEntity(
                    "section1"
            );

            sectionRepository.saveAll(
                    List.of(aDefault, section1)
            );

            AdvertisementEntity add1 = new AdvertisementEntity(
                    user1,
                    "add1",
                    1,
                    "desc1",
                    region3,
                    city1,
                    aDefault
            );

            AdvertisementEntity add2 = new AdvertisementEntity(
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
