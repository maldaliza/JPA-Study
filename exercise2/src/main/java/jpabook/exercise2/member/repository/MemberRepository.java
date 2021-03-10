package jpabook.exercise2.member.repository;

import jpabook.exercise2.member.domain.Member;

public interface MemberRepository {

    /**
     * 회원 저장
     */
    void save(Member member);

    /**
     * 회원 ID로 회원 조회
     */
    Member findById(Long memberId);
}
