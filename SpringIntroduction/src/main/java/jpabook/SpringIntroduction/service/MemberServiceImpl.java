package jpabook.SpringIntroduction.service;

import jpabook.SpringIntroduction.domain.Member;
import jpabook.SpringIntroduction.repository.MemberRepository;
import jpabook.SpringIntroduction.repository.implementation.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    @Override
    public Long join(Member member) {
        validateDuplicateMember(member);        // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    @Override
    public List<Member> findMembers() {
        List<Member> result = memberRepository.findAll();
        return result;
    }

    /**
     * 회원 1명 조회(test 용도)
     */
    @Override
    public Optional<Member> findOne(Long memberId) {
        Optional<Member> result = memberRepository.findById(memberId);
        return result;
    }
}
