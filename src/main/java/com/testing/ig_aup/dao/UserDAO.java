package com.testing.ig_aup.dao;

import com.testing.ig_aup.model.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> findByUsernameAndPassword(String username, String password);
    void updateUsernameAndPassword(Long userId, String newUsername, String newPassword);
}
