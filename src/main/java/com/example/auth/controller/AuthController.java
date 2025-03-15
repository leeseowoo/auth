package com.example.auth.controller;

import com.example.auth.service.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupDto.SignupResponse> signup(@RequestBody SignupDto.SignupRequest request) {
        SignupCommand command = request.toCommand();
        SignupInfo info = authService.signUp(command);
        SignupDto.SignupResponse response = new SignupDto.SignupResponse(info);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<Void> signin(@RequestBody SigninDto.SigninRequest request, HttpServletResponse httpServletResponse) {
        SigninCommand command = request.toCommand();
        authService.signIn(command, httpServletResponse);
        return ResponseEntity.noContent().build();
    }
}
