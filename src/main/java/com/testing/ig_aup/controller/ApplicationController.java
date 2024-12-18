package com.testing.ig_aup.controller;

import com.testing.ig_aup.model.Customer;
import com.testing.ig_aup.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String showApplicationListPage(
            @RequestParam(value = "filter", required = false) String filter,
            Model model) {
        List<Customer> customers;

        // Apply filter logic
        if ("pending".equalsIgnoreCase(filter)) {
            customers = customerService.getPendingApplications(); // Not reviewed
        } else if ("completed".equalsIgnoreCase(filter)) {
            customers = customerService.getReviewedApplications(); // Accepted or Rejected
        } else {
            customers = customerService.getAllCustomers(); // All applications
        }

        model.addAttribute("customers", customers);
        return "application-list";
    }

    @GetMapping("/{id}")
    public String showApplicationDetailsPage(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
        model.addAttribute("customer", customer);
        return "application-details";
    }

    @PostMapping("/{id}/status")
    @ResponseBody
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        customerService.updateCustomerStatus(id, status);
        return ResponseEntity.ok().build();
    }




}

