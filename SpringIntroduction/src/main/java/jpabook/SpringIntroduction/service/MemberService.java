package jpabook.SpringIntroduction.service;

import jpabook.SpringIntroduction.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    // 회원 가입
    Long join(Member member);

    // 회원 조회
    List<Member> findMembers();

    // 회원 1명 조회(test용도)
    Optional<Member> findOne(Long memberId);
}
