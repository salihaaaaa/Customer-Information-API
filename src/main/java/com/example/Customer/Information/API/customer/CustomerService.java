package com.example.Customer.Information.API.customer;

import com.example.Customer.Information.API.exception.CustomerNotFoundException;
import com.example.Customer.Information.API.exception.EmailAlreadyExistsException;
import com.example.Customer.Information.API.exception.IdNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Customer addCustomer(Customer customer) {
        Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());

        if (customerByEmail.isPresent()) {
            throw new EmailAlreadyExistsException("Email " + customerByEmail.get().getEmail() + " already exists!");
        }

        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;
    }

    public void deleteCustomer(Long customerId) {
        boolean isExists = customerRepository.existsById(customerId);

        if (!isExists) {
            throw new IdNotFoundException("Id " + customerId + " not found");
        }

        customerRepository.deleteById(customerId);

    }
}
