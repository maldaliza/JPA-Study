package com.maldaliza.MvcExercise.controller;

import com.maldaliza.MvcExercise.AppConfig;
import com.maldaliza.MvcExercise.domain.Item;
import com.maldaliza.MvcExercise.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/items")
@Slf4j
public class ItemController {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    ItemRepository itemRepository = applicationContext.getBean("itemRepository", ItemRepository.class);

    /**
     * 상품 목록
     * @param model
     * @return
     */
    @GetMapping
    public String items(Model model) {

        log.info("Show Item List");

        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "/items";
    }

    /**
     * 상품 상세보기
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("{itemId}")
    public String item(@PathVariable Long itemId, Model model) {

        log.info("Show Item Details about ID={}", itemId);

        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

        return "/item";
    }

    /**
     * 상품 등록 (GET)
     * @return
     */
    @GetMapping("/add")
    public String addForm() {

        log.info("Add Item");

        return "/addItem";
    }

    /**
     * 상품 등록 (POST)
     * @param item
     * @return
     */
    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item) {

        log.info("Successfully Added Item");

        itemRepository.save(item);

        return "redirect:/items/" + item.getId();
    }

    /**
     * 상품 수정 (GET)
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/{itemId}/update")
    public String updateForm(@PathVariable Long itemId, Model model) {

        log.info("Item ID to be updated={}", itemId);

        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);

        return "updateItem";
    }

    /**
     * 상품 수정 (POST)
     * @param itemId
     * @param item
     * @return
     */
    @PostMapping("/{itemId}/update")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute Item item) {

        log.info("Successfully Updated Item");

        itemRepository.update(itemId, item);

        return "redirect:/items/{itemId}";
    }

    /**
     * 상품 삭제
     * @param itemId
     * @return
     */
    @PostMapping("/{itemId}")
    public String deleteItem(@PathVariable Long itemId) {

        log.info("Successfully Deleted Item");

        itemRepository.deleteById(itemId);

        return "redirect:/items";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("Java Programming", 10000, 10));
        itemRepository.save(new Item("HTTP Guide", 20000, 20));
    }
}
