package com.bh.workouts.bhworkoutapp.config;

import com.bh.workouts.bhworkoutapp.models.User;
import com.bh.workouts.bhworkoutapp.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthInitiator {

    private final UserService userService;

    public AuthInitiator(UserService userService) {
        this.userService = userService;
    }

    public User getUserFromAuth() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return userService.findUserByLogin(auth.getName());
    }
}