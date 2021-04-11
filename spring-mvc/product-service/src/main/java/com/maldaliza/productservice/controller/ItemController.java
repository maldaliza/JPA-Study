package com.maldaliza.productservice.controller;

import com.maldaliza.productservice.domain.Item;
import com.maldaliza.productservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    /**
     * Welcome 페이지, 상품 목록
     * @param model
     * @return
     */
    @GetMapping("/")
    public String list(Model model) {
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("items", itemList);
        return "itemservice/list";
    }

    /**
     * 상품 상세보기
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/detail/{itemId}")
    public String detail(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "itemservice/detail";
    }

    /**
     * 상품 등록 폼 (GET)
     * @return
     */
    @GetMapping("/add")
    public String addForm() {
        return "itemservice/addForm";
    }

    /**
     * 상품 등록 처리 (POST)
     * @param item
     * @return
     */
    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/detail/" + item.getId();
    }

    /**
     * 상품 수정 폼 (GET)
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/edit/{itemId}")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "itemservice/editForm";
    }

    /**
     * 상품 수정 처리 (POST)
     * @param itemId
     * @param item
     * @return
     */
    @PostMapping("/edit/{itemId}")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/detail/{itemId}";
    }

    /**
     * 상품 삭제
     * @param itemId
     * @return
     */
    @PostMapping("/detail/{itemId}")
    public String deleteItem(@PathVariable Long itemId) {
        itemRepository.deleteById(itemId);
        return "redirect:/";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("Java Programming", 10000, 20));
        itemRepository.save(new Item("HTTP Guide", 15000, 10));
    }
}
