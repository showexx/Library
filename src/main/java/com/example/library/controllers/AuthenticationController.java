package com.example.library.controllers;

import com.example.library.models.User;
import com.example.library.services.RegistrationService;
import com.example.library.util.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserValidator userValidator;
    private final RegistrationService registrationService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if(bindingResult.hasErrors()){
            return "registration";
        }

        registrationService.register(user);
        return "redirect:/api/v1/auth/login";
    }
}
