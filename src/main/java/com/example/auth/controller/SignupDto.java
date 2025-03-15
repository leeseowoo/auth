package com.example.auth.controller;

import com.example.auth.service.SignupCommand;
import com.example.auth.service.SignupInfo;
import lombok.Getter;

public class SignupDto {

    @Getter
    public static class SignupRequest {

        private String username;
        private String password;
        private String slackId;

        public SignupCommand toCommand() {
            return SignupCommand.builder()
                    .username(username)
                    .password(password)
                    .slackId(slackId)
                    .build();
        }
    }

    @Getter
    public static class SignupResponse {

        private final Integer userId;
        private final String username;
        private final String slackId;

        public SignupResponse(SignupInfo info) {
            this.userId = info.getUserId();
            this.username = info.getUsername();
            this.slackId = info.getSlackId();
        }
    }
}
