package jpabook.core.member;

import jpabook.core.member.domain.Grade;
import jpabook.core.member.domain.Member;
import jpabook.core.member.service.MemberService;
import jpabook.core.member.service.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
