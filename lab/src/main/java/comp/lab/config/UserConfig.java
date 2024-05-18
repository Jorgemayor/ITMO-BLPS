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
                    "admin",
                    Role.ADMIN,
                    LocalDate.of(2000, Month.MAY, 19)
            );

            User user1 = new User(
                    "user1",
                    "user1@mail.com",
                    "user1",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            User user2 = new User(
                    "user2",
                    "user2@mail.com",
                    "user2",
                    LocalDate.of(2000, Month.JUNE, 5)
            );

            userRepository.saveAll(
                    List.of(admin, user1, user2)
            );
        };
    }
}
