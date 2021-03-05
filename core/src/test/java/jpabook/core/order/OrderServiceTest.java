package jpabook.core.order;

import jpabook.core.member.domain.Grade;
import jpabook.core.member.domain.Member;
import jpabook.core.member.service.MemberService;
import jpabook.core.member.service.MemberServiceImpl;
import jpabook.core.order.domain.Order;
import jpabook.core.order.service.OrderService;
import jpabook.core.order.service.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
