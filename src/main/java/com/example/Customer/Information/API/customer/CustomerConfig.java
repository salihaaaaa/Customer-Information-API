package com.example.Customer.Information.API.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            Customer anna = new Customer(
                    "Anna",
                    "Anna.example.com",
                    Gender.FEMALE,
                    LocalDate.of(2000, Month.APRIL, 17)
            );

            Customer mariam = new Customer(
                    "Mariam",
                    "Mariam.example.com",
                    Gender.FEMALE,
                    LocalDate.of(1999, Month.DECEMBER, 25)
            );

            Customer mike = new Customer(
                    "Mike",
                    "Mike.example.com",
                    Gender.MALE,
                    LocalDate.of(2003, Month.JANUARY, 5)
            );

            Customer irfan = new Customer(
                    "Irfan",
                    "Irfan.example.com",
                    Gender.MALE,
                    LocalDate.of(2002, Month.SEPTEMBER, 10)
            );

            customerRepository.saveAll(
                    List.of(anna, mariam, mike, irfan)
            );
        };
    }
}
