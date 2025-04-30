package com.aplazo.challenge.exception;

import static com.aplazo.challenge.exception.ErrorCode.RATE_LIMIT_ERROR;
import static com.aplazo.challenge.exception.ErrorCode.RATE_LIMIT_ERROR_CODE;

public class RateLimitException extends BaseException {
    public RateLimitException(String message) {
        super(RATE_LIMIT_ERROR_CODE, RATE_LIMIT_ERROR, message);
    }
}
