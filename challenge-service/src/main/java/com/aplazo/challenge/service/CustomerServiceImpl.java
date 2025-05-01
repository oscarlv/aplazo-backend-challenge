package com.aplazo.challenge.service;

import com.aplazo.challenge.dto.CustomerRequest;
import com.aplazo.challenge.dto.CustomerResponse;
import com.aplazo.challenge.entity.Customer;
import com.aplazo.challenge.exception.CustomerNotFoundException;
import com.aplazo.challenge.exception.InvalidCustomerRequestException;
import com.aplazo.challenge.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import static com.aplazo.challenge.exception.ErrorCode.INVALID_CUSTOMER_REQUEST_MESSAGE_AGE_LIMIT;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {

        LocalDate birthDate = LocalDate.parse(request.getDateOfBirth());
        int age = calculateAge(birthDate);
        double creditLine = assignCreditLineByAge(age);

        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .secondLastName(request.getSecondLastName())
                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
                .creditLineAmount(creditLine)
                .availableCreditLineAmount(creditLine)
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
                .orElseThrow(() -> new CustomerNotFoundException(id));

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
            throw new CustomerNotFoundException(customerId);
        }
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private double assignCreditLineByAge(int age) {
        if (age < 18 || age > 65) {
            throw new InvalidCustomerRequestException(INVALID_CUSTOMER_REQUEST_MESSAGE_AGE_LIMIT);
        }

        if (age <= 25) return 3000.00;
        if (age <= 30) return 5000.00;
        return 8000.00;
    }
}
