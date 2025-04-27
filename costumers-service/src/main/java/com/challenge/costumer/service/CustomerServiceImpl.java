package com.challenge.costumer.service;

import com.challenge.costumer.dto.CustomerRequest;
import com.challenge.costumer.dto.CustomerResponse;
import com.challenge.costumer.entity.Customer;
import com.challenge.costumer.exception.CustomerNotFoundException;
import com.challenge.costumer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .secondLastName(request.getSecondLastName())
                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
                .creditLineAmount(1000.00)
                .availableCreditLineAmount(1000.00)
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerResponse.builder()
                .id(savedCustomer.getId())
                .creditLineAmount(savedCustomer.getCreditLineAmount())
                .availableCreditLineAmount(savedCustomer.getAvailableCreditLineAmount())
                .createdAt(savedCustomer.getCreatedAt().toInstant().toString())
                .build();
    }

    @Override
    public CustomerResponse getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));

        return CustomerResponse.builder()
                .id(customer.getId())
                .creditLineAmount(customer.getCreditLineAmount())
                .availableCreditLineAmount(customer.getAvailableCreditLineAmount())
                .createdAt(customer.getCreatedAt().toString())
                .build();
    }
}
