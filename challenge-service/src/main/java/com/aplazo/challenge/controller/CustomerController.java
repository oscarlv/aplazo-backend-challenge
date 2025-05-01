package com.aplazo.challenge.controller;

import com.aplazo.challenge.dto.CustomerRequest;
import com.aplazo.challenge.dto.CustomerResponse;
import com.aplazo.challenge.dto.TokenResponse;
import com.aplazo.challenge.service.CustomerService;
import com.aplazo.challenge.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        CustomerResponse customerResponse = customerService.createCustomer(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/v1/customers/" + customerResponse.getId())
                .body(customerResponse);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable String customerId
    ) {
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestParam String username, @RequestParam String password) {
        if ("admin".equals(username) && "password".equals(password)) {
            String token = tokenService.generateToken(username);
            return ResponseEntity.ok(new TokenResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
