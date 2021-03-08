package jpabook.exercise.member.service.implementation;

import jpabook.exercise.member.domain.Member;
import jpabook.exercise.member.repository.MemberRepository;
import jpabook.exercise.member.repository.implementation.MemoryMemberRepository;
import jpabook.exercise.member.service.MemberService;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    /**
     * 회원 조회
     */
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
