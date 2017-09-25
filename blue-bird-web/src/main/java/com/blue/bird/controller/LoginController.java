package com.blue.bird.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jim on 2017/9/19.
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
