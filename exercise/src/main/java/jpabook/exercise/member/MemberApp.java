package jpabook.exercise.member;

import jpabook.exercise.AppConfig;
import jpabook.exercise.member.domain.Grade;
import jpabook.exercise.member.domain.Member;
import jpabook.exercise.member.repository.MemberRepository;
import jpabook.exercise.member.repository.implementation.MemoryMemberRepository;
import jpabook.exercise.member.service.MemberService;
import jpabook.exercise.member.service.implementation.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
