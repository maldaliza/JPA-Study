package jpabook.exercise.discount;

import jpabook.exercise.member.domain.Member;

public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
