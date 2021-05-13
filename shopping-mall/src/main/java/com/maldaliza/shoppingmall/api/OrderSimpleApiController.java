package com.maldaliza.shoppingmall.api;

import com.maldaliza.shoppingmall.domain.Address;
import com.maldaliza.shoppingmall.domain.Order;
import com.maldaliza.shoppingmall.domain.OrderStatus;
import com.maldaliza.shoppingmall.repository.OrderRepository;
import com.maldaliza.shoppingmall.repository.OrderSearch;
import com.maldaliza.shoppingmall.repository.order.simplequery.OrderSimpleQueryDto;
import com.maldaliza.shoppingmall.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * xToOne (ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    /**
     * 엔티티를 직접 노출
     * @return
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();        // Lazy 강제 초기화
            order.getDelivery().getAddress();   // Lazy 강제 초기화
        }
        return all;
    }

    /**
     * 엔티티를 조회해서 DTO로 변환
     * @return
     */
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()       // 스트림 생성
                .map(order -> new SimpleOrderDto(order))    // 중개 연산
                .collect(Collectors.toList());              // 최종 연산
        return result;
    }

    /**
     * 엔티티를 조회해서 DTO로 변환 (Fetch Join 최적화)
     * @return
     */
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(order -> new SimpleOrderDto(order))     // 엔티티를 DTO로 변환.
                .collect(Collectors.toList());
        return result;
    }

    /**
     * JPA에서 DTO로 바로 조회
     * @return
     */
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }

    /**
     * DTO
     */
    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();         // LAZY 초기화
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); // LAZY 초기화
        }
    }
}
