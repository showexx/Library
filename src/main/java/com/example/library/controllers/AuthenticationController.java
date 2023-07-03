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
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserValidator userValidator;
    private final RegistrationService registrationService;

    @GetMapping("/authorization")
    public String login() {
        return "authorization";
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
        return "redirect:/auth/authorization";
    }
}
