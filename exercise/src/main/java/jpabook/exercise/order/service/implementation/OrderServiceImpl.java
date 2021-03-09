package jpabook.exercise.order.service.implementation;

import jpabook.exercise.discount.DiscountPolicy;
import jpabook.exercise.member.domain.Member;
import jpabook.exercise.member.repository.MemberRepository;
import jpabook.exercise.order.domain.Order;
import jpabook.exercise.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /**
     * 주문 생성
     */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        Order order = new Order(memberId, itemName, itemPrice, discountPrice);
        return order;
    }
}
