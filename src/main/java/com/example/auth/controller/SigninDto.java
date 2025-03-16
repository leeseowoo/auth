package com.example.auth.controller;

import com.example.auth.service.SigninCommand;
import lombok.Getter;

public class SigninDto {

    @Getter
    public static class SigninRequest {

        private String username;
        private String password;

        public SigninCommand toCommand() {
            return SigninCommand.builder()
                    .username(username)
                    .password(password)
                    .build();
        }
    }
}
