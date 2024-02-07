package com.example.Customer.Information.API.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();

        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @GetMapping(path = "{customerId}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("customerId") Long customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.addCustomer(customer);

        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomer(customerId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("customerId") Long customerId,
                                                   @RequestParam(required = false) String name,
                                                   @RequestParam(required = false) String email,
                                                   @RequestParam(required = false) Gender gender,
                                                   @RequestParam(required = false) LocalDate dateOfBirth) {
        customerService.updateCustomer(customerId, name, email, gender, dateOfBirth);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
