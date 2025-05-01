package com.aplazo.challenge.controller;

import com.aplazo.challenge.dto.LoanRequest;
import com.aplazo.challenge.dto.LoanResponse;
import com.aplazo.challenge.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/loans")
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(
            @RequestBody LoanRequest request
    ) {
        LoanResponse response = loanService.createLoan(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponse> getLoanById(
            @PathVariable String loanId
    ) {
        LoanResponse response = loanService.getLoanById(loanId);
        return ResponseEntity.ok(response);
    }
}
