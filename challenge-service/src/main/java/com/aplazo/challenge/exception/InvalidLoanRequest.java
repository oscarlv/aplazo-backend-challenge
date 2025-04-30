package com.aplazo.challenge.exception;

import static com.aplazo.challenge.exception.ErrorCode.INVALID_LOAN_REQUEST;
import static com.aplazo.challenge.exception.ErrorCode.INVALID_LOAN_REQUEST_CODE;

public class InvalidLoanRequest extends BaseException {
    public InvalidLoanRequest(String message) {
        super(INVALID_LOAN_REQUEST_CODE, INVALID_LOAN_REQUEST, message);
    }
}
