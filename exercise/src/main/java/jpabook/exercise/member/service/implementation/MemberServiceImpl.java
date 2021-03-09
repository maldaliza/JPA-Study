package jpabook.exercise.member.service.implementation;

import jpabook.exercise.member.domain.Member;
import jpabook.exercise.member.repository.MemberRepository;
import jpabook.exercise.member.service.MemberService;
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
        return memberRepository.findById(memberId);
    }
}
