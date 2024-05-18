package comp.lab.config;

import comp.lab.model.Role;
import comp.lab.model.User;
import comp.lab.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
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
        };
    }
}
