package kr.ron2.security.member.presentation;

import kr.ron2.security.member.application.MemberService;
import kr.ron2.security.member.application.dto.SignUpRequest;
import kr.ron2.security.member.application.dto.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {

        return memberService.signUp(signUpRequest);
    }


}
