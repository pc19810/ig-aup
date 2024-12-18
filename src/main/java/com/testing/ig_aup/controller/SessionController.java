package com.testing.ig_aup.controller;

import com.testing.ig_aup.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController {

    @GetMapping("/api/session/user")
    public User getSessionUser(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            throw new RuntimeException("No user is currently logged in");
        }
        return loggedInUser;
    }
}
