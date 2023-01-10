package kr.ron2.security.member.infra;

import kr.ron2.security.member.domain.Member;
import kr.ron2.security.member.domain.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends MemberRepository, JpaRepository<Member, Long> {

    Optional<Member> findByOauthId(String oauthId);
}
