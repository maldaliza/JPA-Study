package jpabook.exercise2.member.repository.implementation;

import jpabook.exercise2.member.domain.Member;
import jpabook.exercise2.member.repository.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    /**
     * 회원 저장
     */
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    /**
     * 회원 ID로 회원 조회
     */
    @Override
    public Member findById(Long memberId) {
        Member findMember = store.get(memberId);
        return findMember;
    }
}
