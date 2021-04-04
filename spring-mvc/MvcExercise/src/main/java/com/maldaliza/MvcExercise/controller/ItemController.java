package com.maldaliza.MvcExercise.controller;

import com.maldaliza.MvcExercise.AppConfig;
import com.maldaliza.MvcExercise.domain.Item;
import com.maldaliza.MvcExercise.repository.ItemRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    ItemRepository itemRepository = applicationContext.getBean("itemRepository", ItemRepository.class);

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "/items";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("Java Programming", 10000, 10));
        itemRepository.save(new Item("HTTP Guide", 20000, 20));
    }
}
