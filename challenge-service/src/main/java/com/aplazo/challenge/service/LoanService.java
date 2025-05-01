package com.aplazo.challenge.service;

import com.aplazo.challenge.dto.LoanRequest;
import com.aplazo.challenge.dto.LoanResponse;

public interface LoanService {
    LoanResponse createLoan(LoanRequest request);
    LoanResponse getLoanById(String loanId);
}
