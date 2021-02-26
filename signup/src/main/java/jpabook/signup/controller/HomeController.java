package jpabook.signup.controller;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    Logger log = Logger.getLogger(getClass());

    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "home";
    }
}
