package comp.lab.controllers;

import comp.lab.dto.LoginRequestDto;
import comp.lab.dto.LoginResponseDto;
import comp.lab.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody @Validated LoginRequestDto request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }
}
