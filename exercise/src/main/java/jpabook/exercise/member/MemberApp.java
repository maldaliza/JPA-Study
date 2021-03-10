package jpabook.exercise.member;

import jpabook.exercise.AppConfig;
import jpabook.exercise.member.domain.Grade;
import jpabook.exercise.member.domain.Member;
import jpabook.exercise.member.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

        /*
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
         */

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberServiceImpl", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
