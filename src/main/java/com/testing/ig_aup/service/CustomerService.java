package com.testing.ig_aup.service;

import com.testing.ig_aup.dao.CustomerDAO;
import com.testing.ig_aup.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDao;


    public Optional<Customer> getCustomerById(Long id) {
        return customerDao.findCustomerById(id);
    }

    public void updateCustomerStatus(Long id, String status) {
        // Update status in the database
        Customer customer = customerDao.findCustomerById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setStatus(status);
        customerDao.updateCustomerStatus(id, status);
    }


    public List<Customer> getAllCustomers() {
        return customerDao.findAllCustomers();
    }

    public List<Customer> getPendingApplications() {
        return customerDao.findCustomersByStatus("Not Reviewed");
    }

    public List<Customer> getAcceptedApplications() {
        return customerDao.findCustomersByStatus("Accepted");
    }
    public List<Customer> getRejectedApplications() {
        return customerDao.findCustomersByStatus("Rejected");
    }

    public List<Customer> getReviewedApplications() {
        return customerDao.findCustomersByStatuses(List.of("Accepted", "Rejected"));
    }

    public long getApplicationsCountByStatus(String status) {
        return customerDao.getApplicationsCountByStatus(status);
    }

    public void updateCustomerDetails(Long id, Customer updatedCustomer) {
        customerDao.updateCustomerDetails(id, updatedCustomer);
    }

    public void addCustomer(Customer newCustomer) {
        customerDao.insertCustomer(newCustomer);
    }
}

