package com.aplazo.challenge.exception;

import static com.aplazo.challenge.exception.ErrorCode.LOAN_NOT_FOUD_CODE;
import static com.aplazo.challenge.exception.ErrorCode.LOAN_NOT_FOUND;

public class LoanNotFoundException  extends BaseException {
    public LoanNotFoundException(String message) {
        super(LOAN_NOT_FOUD_CODE, LOAN_NOT_FOUND, message);
    }
}
