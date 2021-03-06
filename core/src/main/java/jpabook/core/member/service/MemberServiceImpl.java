package jpabook.core.member.service;

import jpabook.core.member.domain.Member;
import jpabook.core.member.repository.MemberRepository;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) { memberRepository.save(member); }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
