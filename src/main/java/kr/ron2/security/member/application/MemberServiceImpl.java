package kr.ron2.security.member.application;

import kr.ron2.security.member.application.dto.MemberSimpleInfo;
import kr.ron2.security.member.domain.Member;
import kr.ron2.security.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public MemberSimpleInfo findOne(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(IllegalArgumentException::new);

        return new MemberSimpleInfo(member.getId(),
                member.getName(),
                member.getEmail(),
                member.getProfileImage());
    }


}
