package com.challenge.costumer.exception;

import static com.challenge.costumer.exception.ErrorCode.CUSTOMER_NOT_FOUND;
import static com.challenge.costumer.exception.ErrorCode.CUSTOMER_NOT_FOUND_CODE;

public class CustomerNotFoundException extends BaseException{
    public CustomerNotFoundException(String message) {
        super(CUSTOMER_NOT_FOUND_CODE, CUSTOMER_NOT_FOUND, message);
    }
}
