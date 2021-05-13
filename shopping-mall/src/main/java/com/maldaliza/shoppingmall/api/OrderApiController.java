package com.maldaliza.shoppingmall.api;

import com.maldaliza.shoppingmall.domain.Order;
import com.maldaliza.shoppingmall.domain.OrderItem;
import com.maldaliza.shoppingmall.repository.OrderRepository;
import com.maldaliza.shoppingmall.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

    /**
     * 엔티티를 직접 노출
     * @return
     */
    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();

            // 주문과 관련된 orderItems를 다 가져와서 LAZY 강제 초기화.
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(orderItem -> orderItem.getItem().getName());
        }
        return all;
    }
}
