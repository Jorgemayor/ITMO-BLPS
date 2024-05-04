package comp.lab.modules;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Integer age;
    private Role role;
    private LocalDate dob;

    public User() {
        role = Role.INACTIVE;
    }

    public User(String name, String email, String password, Integer age, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.dob = dob;
    }
}
