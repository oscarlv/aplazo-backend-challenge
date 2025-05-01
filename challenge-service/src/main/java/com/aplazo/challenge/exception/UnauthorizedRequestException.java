package com.aplazo.challenge.exception;

import static com.aplazo.challenge.exception.ErrorCode.*;

public class UnauthorizedRequest extends BaseException{
    public UnauthorizedRequest(String message) {
        super(UNAUTHORIZED_CODE, UNAUTHORIZED, message);
    }
}
