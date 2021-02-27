package jpabook.SpringIntroduction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello(Model model) {
        model.addAttribute("data", "재국님");
        return "hello";
    }

    /**
     * MVC
     */
    @GetMapping(value = "/hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /**
     * API (@ResponseBody 문자 반환)
     */
    @GetMapping(value = "/hello-string")
    @ResponseBody       // http의 body에 문자 내용을 직접 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    /**
     * API (@ResponseBody 객체 반환)
     */
    @GetMapping(value = "/hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {

        // 객체 생성
        Hello hello = new Hello();
        hello.setName(name);

        return hello;       // 객체를 반환
    }

    class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * @ResponseBody를 사용
     * 1. http의 body에 문자 내용을 직접 반환
     * 2. viewResolver 대신에 HttpMessageConverter가 동작
     */
}
