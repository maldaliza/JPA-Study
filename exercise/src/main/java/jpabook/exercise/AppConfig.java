package jpabook.exercise;

import jpabook.exercise.discount.DiscountPolicy;
import jpabook.exercise.discount.implementation.FixDiscountPolicy;
import jpabook.exercise.member.repository.MemberRepository;
import jpabook.exercise.member.repository.implementation.MemoryMemberRepository;
import jpabook.exercise.member.service.MemberService;
import jpabook.exercise.member.service.implementation.MemberServiceImpl;
import jpabook.exercise.order.service.OrderService;
import jpabook.exercise.order.service.implementation.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {

    /*
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
     */
}
