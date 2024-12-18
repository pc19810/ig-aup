package com.testing.ig_aup.dao.impl;

import com.testing.ig_aup.dao.UserDAO;
import com.testing.ig_aup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_BY_USERNAME_AND_PASSWORD =
            "SELECT * FROM ig_aup.users WHERE username = ? AND password = ?";


    private static final String UPDATE_USERNAME_PASSWORD_QUERY =
            "UPDATE ig_aup.users SET username = ?, password = ? WHERE id = ?";

    @Override
    public void updateUsernameAndPassword(Long userId, String newUsername, String newPassword) {
        try {
            int rowsAffected = jdbcTemplate.update(UPDATE_USERNAME_PASSWORD_QUERY, newUsername, newPassword, userId);
            if (rowsAffected == 0) {
                throw new RuntimeException("No user found with ID: " + userId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update username and password for user ID: " + userId, e);
        }
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return jdbcTemplate.query(FIND_BY_USERNAME_AND_PASSWORD, new Object[]{username, password}, new UserRowMapper())
                .stream()
                .findFirst();
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            return user;
        }
    }
}
