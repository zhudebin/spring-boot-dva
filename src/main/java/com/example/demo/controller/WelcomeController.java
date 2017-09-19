package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jim on 2017/9/18.
 */
@Controller
public class WelcomeController {

    @GetMapping("/")
    public String welcome(){
        return "redirect:/index.html";
    }
}