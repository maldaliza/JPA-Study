package jpabook.exercise.discount.implementation;

import jpabook.exercise.discount.DiscountPolicy;
import jpabook.exercise.member.domain.Grade;
import jpabook.exercise.member.domain.Member;
import org.springframework.stereotype.Service;

@Service
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    /**
     * @return 할인 대상 금액
     */
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
