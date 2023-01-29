package kr.ron2.security.auth;

import kr.ron2.security.member.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
@Getter
public class UserInfo {

    private final String oauthId;
    private final String name;
    private final String email;
    private final String profileImage;

    public Member toMember() {
        return Member.of(oauthId, name, email, profileImage);
    }

}
