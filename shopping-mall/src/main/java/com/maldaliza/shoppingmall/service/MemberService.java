package com.maldaliza.shoppingmall.service;

import com.maldaliza.shoppingmall.domain.Member;
import com.maldaliza.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)      // JPA의 데이터 변경 혹은 로직들은 트랜잭션 안에서 이루어져야 한다.
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * @param member
     * @return
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);      // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     * @param memberId
     * @return
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /**
     * 회원 수정 - 변경 감지 (Dirty Checking)
     * @param id
     * @param name
     */
    @Transactional
    public void update(Long id, String name) {

        // 영속 상태의 회원을 찾아온다.
        Member member = memberRepository.findOne(id);
        member.setName(name);

        // 트랜잭션 커밋 시점에서 변경 감지(Dirty Checking)으로 DB에 UPDATE SQL 실행.
    }
}
