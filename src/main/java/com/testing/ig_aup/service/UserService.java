package com.testing.ig_aup.service;

import com.testing.ig_aup.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;


    public void updateUserSettings(Long userId, String newUsername, String newPassword) {
        if (newUsername == null || newPassword == null || newUsername.isEmpty() || newPassword.isEmpty()) {
            throw new IllegalArgumentException("Username and password cannot be empty.");
        }

        // Additional validation can be added here (e.g., password strength)
        userDAO.updateUsernameAndPassword(userId, newUsername, newPassword);

    }

}
