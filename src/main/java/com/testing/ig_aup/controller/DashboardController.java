package com.testing.ig_aup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/tasks")
    public String showTaskList() {
        // Returns the task-list.html page
        return "task-list.html";
    }

    @GetMapping("/reports")
    public String showReports() {
        // Returns the reports.html page
        return "reports.html";
    }

    @GetMapping("/settings")
    public String showSettings() {
        // Returns the task-list.html page
        return "settings.html";
    }
}

