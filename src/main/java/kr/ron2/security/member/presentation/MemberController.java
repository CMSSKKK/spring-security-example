package kr.ron2.security.member.presentation;

import kr.ron2.security.member.application.MemberService;
import kr.ron2.security.member.application.dto.MemberSimpleInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public MemberSimpleInfo getMyInfos(Long memberId) {
        return memberService.findOne(memberId);
    }


}
