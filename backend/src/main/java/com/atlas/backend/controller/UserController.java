package com.atlas.backend.controller;

import com.atlas.backend.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/api/users/me")
    public String me(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return "Hello " + user.getFirstName();

    }
}