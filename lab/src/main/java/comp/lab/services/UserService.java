package comp.lab.services;

import comp.lab.exceptions.EmailAlreadyExistsException;
import comp.lab.exceptions.UserNotFoundException;
import comp.lab.model.Role;
import comp.lab.model.User;
import comp.lab.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void addUser(User user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new EmailAlreadyExistsException("Email already taken!");
        }

        LocalDate dob = user.getDob();
        if (!dob.isBefore(LocalDate.now())) {
            throw new IllegalStateException("Birthdate not acceptable.");
        }

        if (user.getRole() == null || user.getRole().name().equals("INACTIVE")) {
            user.setRole(Role.USER);
        }
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email, String password, String dob) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user with id " + userId + " does not exists.")
        );

        if (name != null && !name.isEmpty() && !name.equals(user.getName())) {
            user.setName(name);
        }

        if (email != null && !email.isEmpty() && !email.equals(user.getEmail())) {
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new EmailAlreadyExistsException("email already taken!");
            }

            user.setEmail(email);
        }

        if (password != null && password.length() >= 8 && !password.equals(user.getPassword())) {
            user.setPassword(password);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        if (dob != null && !dob.isEmpty()) {
            LocalDate localDate = LocalDate.parse(dob, formatter);
            if (localDate.equals(user.getDob())) {
                user.setDob(localDate);
            }
        }
    }

    public void updateUserRole(Long userId, Role role) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user with id " + userId + " does not exists.")
        );
        user.setRole(role);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new UserNotFoundException("user with id " + userId + " does not exists.");
        }
        userRepository.deleteById(userId);
    }
}
