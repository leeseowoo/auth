package com.example.auth.service;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SigninCommand {

    private String username;
    private String password;
}
