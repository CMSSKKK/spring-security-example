package kr.ron2.security.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class LoginMember implements OAuth2User {

    private final Long id;
    private final String name;
    private final String email;
    private final String profileImage;
    private final OAuth2User oAuth2User;


    public LoginMember(Long id, String name, String email, String profileImage,
                       OAuth2User oAuth2User) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
        this.oAuth2User = oAuth2User;
    }

    @Override
    public <A> A getAttribute(String name) {
        return oAuth2User.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }
}
