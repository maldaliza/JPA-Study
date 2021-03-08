package jpabook.core.discount;

import jpabook.core.member.domain.Grade;
import jpabook.core.member.domain.Member;
import org.springframework.stereotype.Service;

@Service
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
