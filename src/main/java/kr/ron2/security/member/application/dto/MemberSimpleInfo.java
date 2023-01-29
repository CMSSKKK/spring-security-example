package kr.ron2.security.member.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberSimpleInfo {

    private final Long memberId;
    private final String name;
    private final String email;
    private final String profileImage;


}
