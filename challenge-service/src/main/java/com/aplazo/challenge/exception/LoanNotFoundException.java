package com.aplazo.challenge.exception;

import static com.aplazo.challenge.exception.ErrorCode.*;

public class LoanNotFoundException  extends BaseException {
    public LoanNotFoundException(String id) {
        super(LOAN_NOT_FOUD_CODE, LOAN_NOT_FOUND, String.format(LOAN_NOT_FOUND_MESSAGE, id));
    }
}
