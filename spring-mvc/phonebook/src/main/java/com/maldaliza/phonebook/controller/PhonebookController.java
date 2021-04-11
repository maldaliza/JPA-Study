package com.maldaliza.phonebook.controller;

import com.maldaliza.phonebook.domain.Phone;
import com.maldaliza.phonebook.repository.PhoneNumberRepository;
import com.maldaliza.phonebook.repository.implementation.MemoryPhoneNumberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class PhonebookController {

    private PhoneNumberRepository phoneNumberRepository = new MemoryPhoneNumberRepository();

    @GetMapping("/")
    public String list(Model model) {

        List<Phone> phoneNumberList = phoneNumberRepository.findAll();
        model.addAttribute("phoneNumberList", phoneNumberList);

        return "/phonebook/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long phoneNumberId, Model model) {
        Phone phone = phoneNumberRepository.findById(phoneNumberId);
        model.addAttribute("phone", phone);
        return "/detail";
    }

    @GetMapping("/add")
    public String addForm() {
        return "/phonebook/addForm";
    }

    @PostMapping("/add")
    public String addPhoneNumber(@ModelAttribute Phone phone) {
        phoneNumberRepository.save(phone);
        return "redirect:/detail/" + phone.getId();
    }

    @PostConstruct
    public void init() {
        phoneNumberRepository.save(new Phone("홍길동", "010-9292-5844"));
        phoneNumberRepository.save(new Phone("신짱구", "010-4359-6915"));
    }
}
