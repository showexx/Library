package com.example.library.util;

import com.example.library.models.User;
import com.example.library.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Controller
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        try {
            customUserDetailsService.loadUserByUsername(user.getEmail());
        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("email", "", "User found");
    }
}
