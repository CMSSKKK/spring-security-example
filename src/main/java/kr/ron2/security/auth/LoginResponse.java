package kr.ron2.security.auth;

import lombok.Getter;

@Getter
public class LoginResponse {

    private final Long memberId;
    private final String name;
    private final String profileImage;
    private final String accessToken;
    private final String refreshToken;

    public LoginResponse(LoginMember loginMember, String accessToken, String refreshToken) {
        this.memberId = loginMember.getId();
        this.name = loginMember.getName();
        this.profileImage = loginMember.getProfileImage();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
