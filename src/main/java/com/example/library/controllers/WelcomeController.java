package com.example.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/welcome-private")
    public String welcomePrivate() {
        return "Welcome private";
    }

    @GetMapping("/welcome-public")
    public String welcomePublic() {
        return "Welcome public";
    }
}
