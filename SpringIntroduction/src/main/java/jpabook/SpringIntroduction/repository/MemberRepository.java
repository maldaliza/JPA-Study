package jpabook.SpringIntroduction.repository;

import jpabook.SpringIntroduction.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // 회원 저장
    Member save(Member member);

    // id로 회원 조회
    Optional<Member> findById(Long id);

    // name으로 회원 조회
    Optional<Member> findByName(String name);

    // 전체 회원 목록 조회
    List<Member> findAll();
}
