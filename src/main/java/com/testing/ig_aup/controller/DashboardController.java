package com.testing.ig_aup.controller;

import com.testing.ig_aup.model.Customer;
import com.testing.ig_aup.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/api/dashboard/applications")
    @ResponseBody
    public List<Customer> getRecentApplications() {
        // Fetch the top 10 recent applications
        return customerService.getPendingApplications().stream()
                .limit(10) // Limit to 10 applications
                .toList();
    }


    @GetMapping("/tasks")
    public String showTaskList() {
        return "task-list.html";
    }

    @GetMapping("/reports")
    public String showReports() {
        return "reports.html";
    }

    @GetMapping("/settings")
    public String showSettings() {
        return "settings.html";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "profile.html";
    }
}
