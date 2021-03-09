package jpabook.exercise.order;

import jpabook.exercise.AppConfig;
import jpabook.exercise.member.domain.Grade;
import jpabook.exercise.member.domain.Member;
import jpabook.exercise.member.service.MemberService;
import jpabook.exercise.order.domain.Order;
import jpabook.exercise.order.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberServiceImpl", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderServiceImpl", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order);
    }
}
