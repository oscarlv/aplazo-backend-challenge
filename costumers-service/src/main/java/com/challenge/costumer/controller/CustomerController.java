package com.challenge.costumer.controller;

import com.challenge.costumer.dto.CustomerRequest;
import com.challenge.costumer.dto.CustomerResponse;
import com.challenge.costumer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
        CustomerResponse customerResponse = customerService.createCustomer(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/v1/customers/" + customerResponse.getId())
                .body(customerResponse);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Integer customerId) {
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerResponse);
    }

}
