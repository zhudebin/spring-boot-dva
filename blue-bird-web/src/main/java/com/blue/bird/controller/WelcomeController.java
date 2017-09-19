package com.blue.bird.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jim on 2017/9/19.
 */
@Controller
public class WelcomeController {

    @GetMapping({"/","/login"})
    public String welcome(){
        return "redirect:/index.html";
    }
}
