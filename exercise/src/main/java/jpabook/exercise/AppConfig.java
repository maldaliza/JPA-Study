package jpabook.exercise;

import jpabook.exercise.member.repository.MemberRepository;
import jpabook.exercise.member.repository.implementation.MemoryMemberRepository;
import jpabook.exercise.member.service.MemberService;
import jpabook.exercise.member.service.implementation.MemberServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
