package comp.lab.controllers;

import comp.lab.model.LoginRequest;
import comp.lab.model.LoginResponse;
import comp.lab.model.Role;
import comp.lab.security.JwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtIssuer jwtIssuer;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        var token = jwtIssuer.issue(1L, request.getEmail(), List.of("USER"));
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }
}
