package com.testing.ig_aup.controller;

import com.testing.ig_aup.dao.UserDAO;
import com.testing.ig_aup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login.html"; // Render login.html (no extension required)
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpSession session,
                              Model model) {
        Optional<User> user = userDAO.findByUsernameAndPassword(username, password);

        if (user.isPresent()) {
            // Store user details in session
            session.setAttribute("loggedInUser", user.get());
            return "redirect:/dashboard"; // Redirect to /dashboard endpoint
        } else {
            // Show error message if login fails
            model.addAttribute("error", "Invalid username or password.");
            return "login.html"; // Render login.html (no extension required)
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            // If no user is logged in, redirect to login
            return "redirect:/login";
        }
        // Pass user details to the model
        model.addAttribute("user", loggedInUser);
        return "dashboard.html"; // Render dashboard.html (no extension required)
    }

    @PostMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "redirect:/login"; // Redirect to the login page
    }
}
