package com.aplazo.challenge.service;

import com.aplazo.challenge.dto.CustomerRequest;
import com.aplazo.challenge.dto.CustomerResponse;
import com.aplazo.challenge.entity.Customer;
import com.aplazo.challenge.exception.CustomerNotFoundException;
import com.aplazo.challenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

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
                .id(UUID.randomUUID())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .secondLastName(request.getSecondLastName())
                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
                .creditLineAmount(1000.00)
                .availableCreditLineAmount(1000.00)
                .build();

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerResponse.builder()
                .id(savedCustomer.getId().toString())
                .creditLineAmount(savedCustomer.getCreditLineAmount())
                .availableCreditLineAmount(savedCustomer.getAvailableCreditLineAmount())
                .createdAt(savedCustomer.getCreatedAt().toInstant().toString())
                .build();
    }

    @Override
    public CustomerResponse getCustomerById(String id) {

        UUID uuid = validateAndParseCustomerId(id);
        Customer customer = customerRepository.findById(uuid)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));

        return CustomerResponse.builder()
                .id(customer.getId().toString())
                .creditLineAmount(customer.getCreditLineAmount())
                .availableCreditLineAmount(customer.getAvailableCreditLineAmount())
                .createdAt(customer.getCreatedAt().toString())
                .build();
    }

    private UUID validateAndParseCustomerId(String customerId) {
        try {
            return UUID.fromString(customerId);
        } catch (IllegalArgumentException ex) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
    }
}
