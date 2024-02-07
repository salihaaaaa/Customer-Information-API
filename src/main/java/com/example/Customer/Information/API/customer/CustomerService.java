package com.example.Customer.Information.API.customer;

import com.example.Customer.Information.API.exception.CustomerNotFoundException;
import com.example.Customer.Information.API.exception.EmailAlreadyExistsException;
import com.example.Customer.Information.API.exception.IdNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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

    @Transactional
    public void updateCustomer(Long customerId, String name, String email,
                                   Gender gender, LocalDate dateOfBirth) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IdNotFoundException("customer with id " + customerId + " does not exist")
                );

        if (name != null && !name.isEmpty() && !Objects.equals(customer.getName(), name)) {
            customer.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(customer.getEmail(), email)) {
            Optional<Customer> customerByEmail = customerRepository.findCustomerByEmail(email);

            if(customerByEmail.isPresent()) {
                throw new EmailAlreadyExistsException("email " + customerByEmail.get().getEmail() + " already exist!");
            }

            customer.setEmail(email);
        }

        if (gender != null && !Objects.equals(customer.getGender(), gender)) {
            customer.setGender(gender);
        }

        if (dateOfBirth != null && !Objects.equals(customer.getDateOfBirth(), dateOfBirth)) {
            customer.setDateOfBirth(dateOfBirth);
        }
    }
}
