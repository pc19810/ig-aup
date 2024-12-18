package com.testing.ig_aup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaskController {

    /**
     * Redirect the /tasks endpoint to /applications.
     *
     * @return Redirects to the /applications endpoint.
     */
    @GetMapping("/tasks/applications")
    public String redirectToApplications() {
        return "redirect:/applications"; // Redirect to /applications endpoint
    }
}
