package kr.ron2.security.member.application;

import kr.ron2.security.member.application.dto.SignUpRequest;
import kr.ron2.security.member.application.dto.SignUpResponse;
import kr.ron2.security.member.domain.Member;
import kr.ron2.security.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        Member member = Member.of(signUpRequest.getEmail(), signUpRequest.getPassword());

        return new SignUpResponse(memberRepository.save(member));
    }

}
