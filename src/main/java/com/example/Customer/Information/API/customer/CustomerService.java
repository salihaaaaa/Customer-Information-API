package com.example.Customer.Information.API.customer;

import com.example.Customer.Information.API.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();

        if (allCustomers.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found!");
        }

        return allCustomers;
    }
}
