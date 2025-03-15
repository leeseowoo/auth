package com.example.auth.service;

import com.example.auth.repository.AuthRepository;
import com.example.auth.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public SignupInfo signUp(SignupCommand command) {
        Optional<User> checkUsername = authRepository.findByUsername(command.getUsername());
        if (checkUsername.isPresent()) throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");

        String encodedPassword = passwordEncoder.encode(command.getPassword());
        User user = command.toEntity(encodedPassword);
        User savedUser = authRepository.save(user);

        return new SignupInfo(savedUser);
    }

    public void signIn(SigninCommand command, HttpServletResponse httpServletResponse) {
        User user = authRepository.findByUsername(command.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사용자입니다."));

        if (!passwordEncoder.matches(command.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT를 생성하고 쿠키에 저장한 후 HttpServletResponse에 추가하여 반환
        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
        jwtUtil.addJwtToCookie(token, httpServletResponse);
    }
}