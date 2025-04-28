package com.aplazo.challenge.exception;

import static com.aplazo.challenge.exception.ErrorCode.CUSTOMER_NOT_FOUND;
import static com.aplazo.challenge.exception.ErrorCode.CUSTOMER_NOT_FOUND_CODE;

public class CustomerNotFoundException extends BaseException{
    public CustomerNotFoundException(String message) {
        super(CUSTOMER_NOT_FOUND_CODE, CUSTOMER_NOT_FOUND, message);
    }
}
