package com.testing.ig_aup.controller;

import com.testing.ig_aup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> updateSettings(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        Long userId = 1L; // Replace this with the actual logged-in user's ID

        // Validate input
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username and password cannot be empty."));
        }

        try {
            userService.updateUserSettings(userId, username, password);
            return ResponseEntity.ok(Map.of("message", "Settings updated successfully."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Failed to update settings: " + e.getMessage()));
        }
    }
}
