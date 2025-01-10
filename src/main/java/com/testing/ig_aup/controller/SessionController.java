package com.testing.ig_aup.controller;

import com.testing.ig_aup.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class SessionController {

    @GetMapping("/api/session/user")
    public ResponseEntity<?> getSessionUser(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // Return 401 (Unauthorized) when no user is logged in
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user is currently logged in");
        }
        return ResponseEntity.ok(loggedInUser);
    }
}
