package com.maldaliza.shoppingmall.controller;

import com.maldaliza.shoppingmall.domain.Member;
import com.maldaliza.shoppingmall.domain.Order;
import com.maldaliza.shoppingmall.domain.item.Item;
import com.maldaliza.shoppingmall.repository.OrderSearch;
import com.maldaliza.shoppingmall.service.ItemService;
import com.maldaliza.shoppingmall.service.MemberService;
import com.maldaliza.shoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    /**
     * 주문 생성 폼 (GET)
     *
     * @param model
     * @return
     */
    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    /**
     * 주문 생성 처리 (POST)
     *
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    /**
     * 주문 목록 검색, 취소 (GET)
     *
     * @param orderSearch
     * @param model
     * @return
     */
    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    /**
     * 주문 취소 (POST)
     * @param orderId
     * @return
     */
    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
