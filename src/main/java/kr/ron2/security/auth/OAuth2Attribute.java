package kr.ron2.security.auth;


import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
@RequiredArgsConstructor
public enum OAuth2Attribute {

    GITHUB("github", attributes -> new UserInfo(
            String.valueOf(attributes.get("id")),
            (String) attributes.get("name"),
            (String) attributes.get("email"),
            (String) attributes.get("avatar_url"))
    ),
    GOOGLE("google", attributes -> new UserInfo(
            String.valueOf(attributes.get("sub")),
            (String) attributes.get("name"),
            (String) attributes.get("email"),
            (String) attributes.get("picture"))
    );



    private final String registrationId;
    private final Function<Map<String, Object>, UserInfo> attributeToUserInfo;

    public static UserInfo toUserInfo(String registrationId, Map<String, Object> attributes) {

        return Arrays.stream(values())
                .filter(provider -> provider.registrationId.equals(registrationId))
                .map(p -> p.attributeToUserInfo.apply(attributes))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
