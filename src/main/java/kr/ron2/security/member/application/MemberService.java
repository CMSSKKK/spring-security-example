package kr.ron2.security.member.application;

import kr.ron2.security.member.application.dto.MemberSimpleInfo;

public interface MemberService {

    MemberSimpleInfo findOne(Long memberId);


}
