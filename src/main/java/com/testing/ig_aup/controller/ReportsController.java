package com.testing.ig_aup.controller;

import com.testing.ig_aup.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/rejection-rate")
    public Map<String, Object> getRejectionRateData() {
        long totalApplications = customerService.getAllCustomers().size();
        long rejectedApplications = customerService.getApplicationsCountByStatus("Rejected");
        long acceptedApplications = customerService.getApplicationsCountByStatus("Accepted");
        long notReviewedApplications = customerService.getApplicationsCountByStatus("Not Reviewed");

        Map<String, Object> response = new HashMap<>();
        response.put("totalApplications", totalApplications);
        response.put("rejectedApplications", rejectedApplications);
        response.put("acceptedApplications", acceptedApplications);
        response.put("notReviewedApplications", notReviewedApplications);

        return response;
    }
}