package kr.ron2.security.member.domain;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findByOauthId(String oauthId);
}
