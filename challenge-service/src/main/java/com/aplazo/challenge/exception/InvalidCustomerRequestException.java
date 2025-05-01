package com.aplazo.challenge.exception;

import static com.aplazo.challenge.exception.ErrorCode.INVALID_CUSTOMER_REQUEST;
import static com.aplazo.challenge.exception.ErrorCode.INVALID_CUSTOMER_REQUEST_CODE;

public class InvalidCustomerRequestException extends BaseException {
    public InvalidCustomerRequestException(String message) {
        super(INVALID_CUSTOMER_REQUEST_CODE, INVALID_CUSTOMER_REQUEST, message);
    }
}
