package com.example.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}
