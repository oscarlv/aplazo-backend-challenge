package com.challenge.costumer.controller;

import com.challenge.costumer.dto.CustomerRequest;
import com.challenge.costumer.dto.CustomerResponse;
import com.challenge.costumer.dto.TokenResponse;
import com.challenge.costumer.service.CustomerService;
import com.challenge.costumer.service.TokenService;
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
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody @Valid CustomerRequest request,
            @RequestHeader(value = "X-Auth-Token", required = true) String token
    ) {

        if (!tokenService.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomerResponse customerResponse = customerService.createCustomer(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/v1/customers/" + customerResponse.getId())
                .body(customerResponse);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable Integer customerId,
            @RequestHeader(value = "X-Auth-Token", required = true) String token
    ) {

        if (!tokenService.isTokenValid(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

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
