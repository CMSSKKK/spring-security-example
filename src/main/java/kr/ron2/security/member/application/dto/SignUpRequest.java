package kr.ron2.security.member.application.dto;

import lombok.Getter;

@Getter
public class SignUpRequest {

    private String email;
    private String password;
}
