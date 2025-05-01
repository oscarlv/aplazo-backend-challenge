package com.aplazo.challenge.exception;

import static com.aplazo.challenge.exception.ErrorCode.*;

public class CustomerNotFoundException extends BaseException{
    public CustomerNotFoundException(String id) {
        super(CUSTOMER_NOT_FOUND_CODE, CUSTOMER_NOT_FOUND, String.format(CUSTOMER_NOT_FOUND_MESSAGE, id));
    }
}
