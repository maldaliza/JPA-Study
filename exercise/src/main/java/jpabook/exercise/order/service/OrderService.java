package jpabook.exercise.order.service;

import jpabook.exercise.order.domain.Order;

public interface OrderService {

    /**
     * 주문 생성
     */
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
