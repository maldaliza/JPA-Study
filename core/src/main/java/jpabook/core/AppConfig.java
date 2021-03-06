package jpabook.core;

import jpabook.core.discount.DiscountPolicy;
import jpabook.core.discount.FixDiscountPolicy;
import jpabook.core.discount.RateDiscountPolicy;
import jpabook.core.member.repository.MemberRepository;
import jpabook.core.member.repository.MemoryMemberRepository;
import jpabook.core.member.service.MemberService;
import jpabook.core.member.service.MemberServiceImpl;
import jpabook.core.order.service.OrderService;
import jpabook.core.order.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

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
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
