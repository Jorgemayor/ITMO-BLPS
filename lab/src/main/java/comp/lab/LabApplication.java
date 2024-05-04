package comp.lab;

import comp.lab.modules.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
@RestController
public class LabApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}

	@GetMapping
	public User hello() {
		return new User(
				"a",
				"a",
				"a",
				23,
				LocalDate.of(2000, Month.JANUARY, 5)
		);
	}

}
