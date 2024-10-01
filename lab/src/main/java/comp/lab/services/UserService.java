package comp.lab.services;

import comp.lab.entity.UserEntity;
import comp.lab.exceptions.EmailAlreadyExistsException;
import comp.lab.exceptions.InvalidPasswordException;
import comp.lab.exceptions.UserNotFoundException;
import comp.lab.entity.Role;
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

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public void addUser(UserEntity userEntity) {
        Optional<UserEntity> userOptional = userRepository.findUserByEmail(userEntity.getEmail());
        if (userOptional.isPresent()) {
            throw new EmailAlreadyExistsException("Email already taken!");
        }

        LocalDate dob = userEntity.getDob();
        if (dob == null || !dob.isBefore(LocalDate.now())) {
            throw new IllegalStateException("Birthdate not acceptable.");
        }

        if (userEntity.getRole() == null || userEntity.getRole().name().equals("INACTIVE")) {
            userEntity.setRole(Role.USER);
        }

        if (userEntity.getPassword().isEmpty() || userEntity.getPassword().length() < 6) {
            throw new InvalidPasswordException("Password length not acceptable.");
        }

        userRepository.save(userEntity);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email, String password, String dob) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user with id " + userId + " does not exists.")
        );

        if (name != null && !name.isEmpty() && !name.equals(userEntity.getName())) {
            userEntity.setName(name);
        }

        if (email != null && !email.isEmpty() && !email.equals(userEntity.getEmail())) {
            Optional<UserEntity> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new EmailAlreadyExistsException("email already taken!");
            }

            userEntity.setEmail(email);
        }

        if (password != null && password.length() >= 8 && !password.equals(userEntity.getPassword())) {
            userEntity.setPassword(password);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        if (dob != null && !dob.isEmpty()) {
            LocalDate localDate = LocalDate.parse(dob, formatter);
            if (localDate.equals(userEntity.getDob())) {
                userEntity.setDob(localDate);
            }
        }
    }

    public void updateUserRole(Long userId, Role role) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("user with id " + userId + " does not exists.")
        );
        userEntity.setRole(role);
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new UserNotFoundException("user with id " + userId + " does not exists.");
        }
        userRepository.deleteById(userId);
    }
}
