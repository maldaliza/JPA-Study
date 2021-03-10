package jpabook.exercise2.member.service.implementation;

import jpabook.exercise2.member.domain.Member;
import jpabook.exercise2.member.repository.MemberRepository;
import jpabook.exercise2.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
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
        Member findMember = memberRepository.findById(memberId);
        return findMember;
    }
}
