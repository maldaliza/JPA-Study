package jpabook.core;

import jpabook.core.discount.DiscountPolicy;
import jpabook.core.discount.FixDiscountPolicy;
import jpabook.core.member.repository.MemberRepository;
import jpabook.core.member.repository.MemoryMemberRepository;
import jpabook.core.member.service.MemberService;
import jpabook.core.member.service.MemberServiceImpl;
import jpabook.core.order.service.OrderService;
import jpabook.core.order.service.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
