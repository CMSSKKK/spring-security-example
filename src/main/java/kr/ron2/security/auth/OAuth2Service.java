package kr.ron2.security.auth;

import kr.ron2.security.member.domain.Member;
import kr.ron2.security.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> defaultOAuth2UserService = new DefaultOAuth2UserService();
    private final MemberRepository memberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String nameAttributeKey = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        UserInfo userInfo = OAuth2Attribute.toUserInfo(registrationId, attributes);

        Member member = saveOrUpdate(userInfo);

        return new DefaultOAuth2User(null, attributes, nameAttributeKey);
    }

    private Member saveOrUpdate(UserInfo userInfo) {
        Member member = memberRepository.findByOauthId(userInfo.getOauthId())
                .map(mem -> mem.update(userInfo.getName(), userInfo.getEmail(), userInfo.getProfileImage()))
                .orElseGet(userInfo::toMember);

        return memberRepository.save(member);
    }

}
