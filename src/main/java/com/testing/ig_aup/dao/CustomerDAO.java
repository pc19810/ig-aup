package com.testing.ig_aup.dao;

import com.testing.ig_aup.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> findAllCustomers();

    long getApplicationsCountByStatus(String status);

    Optional<Customer> findCustomerById(Long id);
    void updateCustomerStatus(Long id, String status);

    List<Customer> findCustomersByStatuses(List<String> accepted);

    List<Customer> findCustomersByStatus(String notReviewed);

    public void updateCustomerDetails(Long id, Customer updatedCustomer);

    public void insertCustomer(Customer customer);
}
