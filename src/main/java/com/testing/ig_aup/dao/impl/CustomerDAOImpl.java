package com.testing.ig_aup.dao.impl;

import com.testing.ig_aup.dao.CustomerDAO;
import com.testing.ig_aup.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_ALL_CUSTOMERS = "SELECT * FROM ig_aup.customers";
    private static final String FIND_CUSTOMERS_BY_STATUS = "SELECT * FROM ig_aup.customers WHERE status = ?";
    private static final String FIND_CUSTOMERS_BY_STATUSES = "SELECT * FROM ig_aup.customers WHERE status IN (?)";
    private static final String FIND_CUSTOMER_BY_ID = "SELECT * FROM ig_aup.customers WHERE id = ?";
    private static final String UPDATE_CUSTOMER_STATUS = "UPDATE ig_aup.customers SET status = ? WHERE id = ?";

    @Override
    public List<Customer> findAllCustomers() {
        return jdbcTemplate.query(FIND_ALL_CUSTOMERS, new CustomerRowMapper());
    }

    public List<Customer> findCustomersByStatus(String status) {
        return jdbcTemplate.query(FIND_CUSTOMERS_BY_STATUS, new CustomerRowMapper(), status);
    }

    public List<Customer> findCustomersByStatuses(List<String> statuses) {
        String inClause = String.join(",", statuses.stream().map(s -> "'" + s + "'").toArray(String[]::new));
        String query = FIND_CUSTOMERS_BY_STATUSES.replace("(?)", "(" + inClause + ")");
        return jdbcTemplate.query(query, new CustomerRowMapper());
    }

    @Override
    public long getApplicationsCountByStatus(String status) {
        String query = "SELECT COUNT(*) FROM ig_aup.customers WHERE status = ?";
        return jdbcTemplate.queryForObject(query, Long.class, status);
    }


    @Override
    public Optional<Customer> findCustomerById(Long id) {
        List<Customer> customers = jdbcTemplate.query(FIND_CUSTOMER_BY_ID, new CustomerRowMapper(), id);
        return customers.isEmpty() ? Optional.empty() : Optional.of(customers.get(0));
    }

    @Override
    public void updateCustomerStatus(Long id, String status) {
        jdbcTemplate.update(UPDATE_CUSTOMER_STATUS, status, id);
    }

    private static class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getLong("id"));
            customer.setName(rs.getString("name"));
            customer.setAge(rs.getInt("age"));
            customer.setOccupation(rs.getString("occupation"));
            customer.setInsuranceType(rs.getString("insurance_type"));
            customer.setCoverageAmount(rs.getDouble("coverage_amount"));
            customer.setScore(rs.getInt("score"));
            customer.setCategory(rs.getString("category"));
            customer.setStatus(rs.getString("status"));
            return customer;
        }
    }
}

