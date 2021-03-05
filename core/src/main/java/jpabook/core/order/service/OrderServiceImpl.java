package jpabook.core.order.service;

import jpabook.core.discount.DiscountPolicy;
import jpabook.core.discount.FixDiscountPolicy;
import jpabook.core.member.domain.Member;
import jpabook.core.member.repository.MemberRepository;
import jpabook.core.member.repository.MemoryMemberRepository;
import jpabook.core.order.domain.Order;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 1. 회원 찾기
        Member member = memberRepository.findById(memberId);

        // 2. DiscountPolicy로 회원정보와 상품금액을 넘겨서 "할인 금액"을 반환 받는다.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 3. 주문 생성
        Order order = new Order(memberId, itemName, itemPrice, discountPrice);

        return order;
    }
}
