package com.maldaliza.shoppingmall.api;

import com.maldaliza.shoppingmall.domain.Address;
import com.maldaliza.shoppingmall.domain.Order;
import com.maldaliza.shoppingmall.domain.OrderItem;
import com.maldaliza.shoppingmall.domain.OrderStatus;
import com.maldaliza.shoppingmall.repository.OrderRepository;
import com.maldaliza.shoppingmall.repository.OrderSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
            order.getMember().getName();        // LAZY 강제 초기화
            order.getDelivery().getAddress();   // LAZY 강제 초기화

            // 주문과 관련된 orderItems를 다 가져와서 LAZY 강제 초기화.
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                orderItem.getItem().getName();
            }
//            orderItems.stream().forEach(orderItem -> orderItem.getItem().getName());
        }
        return all;
    }

    /**
     * 엔티티를 조회해서 DTO로 변환
     * @return
     */
    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        // orders -> orderDto
        List<OrderDto> result = orders.stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 엔티티를 조회해서 DTO로 변환 (Fetch Join 최적화)
     * @return
     */
    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithItem();

        // orders -> orderDto
        List<OrderDto> result = orders.stream()
                .map(order -> new OrderDto(order))
                .collect(Collectors.toList());
        return result;
    }

    //=== DTO ===//
    @Getter
    static class OrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();         // LAZY 강제 초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); // LAZY 강제 초기화

            // orderItems -> orderItemDto (엔티티에 대한 의존을 완전히 끊기 위해)
            orderItems = order.getOrderItems().stream()
                    .map(orderItem -> new OrderItemDto(orderItem))
                    .collect(Collectors.toList());
        }
    }

    @Getter
    static class OrderItemDto {
        private String itemName;   // 상품명
        private int orderPrice;    // 주문 가격
        private int count;         // 주문 수량

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            orderPrice = orderItem.getOrderPrice();
            count = orderItem.getCount();
        }
    }
}
