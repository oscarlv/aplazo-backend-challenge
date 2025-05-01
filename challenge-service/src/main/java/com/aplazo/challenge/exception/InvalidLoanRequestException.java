package com.aplazo.challenge.exception;

import static com.aplazo.challenge.exception.ErrorCode.INVALID_LOAN_REQUEST;
import static com.aplazo.challenge.exception.ErrorCode.INVALID_LOAN_REQUEST_CODE;

public class InvalidLoanRequestException extends BaseException {
    public InvalidLoanRequestException(String message) {
        super(INVALID_LOAN_REQUEST_CODE, INVALID_LOAN_REQUEST, message);
    }
}
