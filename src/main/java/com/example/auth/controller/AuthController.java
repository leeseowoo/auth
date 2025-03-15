package com.example.auth.controller;

import com.example.auth.service.AuthService;
import com.example.auth.service.SignupCommand;
import com.example.auth.service.SignupInfo;
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
}
