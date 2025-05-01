package com.aplazo.challenge.exception;

import static com.aplazo.challenge.exception.ErrorCode.*;

public class UnauthorizedRequestException extends BaseException{
    public UnauthorizedRequestException(String message) {
        super(UNAUTHORIZED_CODE, UNAUTHORIZED, message);
    }
}
