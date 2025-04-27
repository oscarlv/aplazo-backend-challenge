package com.challenge.costumer.exception;

public final class ErrorCode {

    private ErrorCode() {}

    public static final String INTERNAL_SERVER_ERROR_CODE = "APZ000001";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

    public static final String INVALID_CUSTOMER_REQUEST_CODE = "APZ000002";
    public static final String INVALID_CUSTOMER_REQUEST = "INVALID_CUSTOMER_REQUEST";

    public static final String CUSTOMER_NOT_FOUND_CODE = "APZ000005";
    public static final String CUSTOMER_NOT_FOUND = "CUSTOMER_NOT_FOUND";

    public static final String RATE_LIMIT_ERROR_CODE = "APZ000003";
    public static final String RATE_LIMIT_ERROR = "RATE_LIMIT_ERROR";
}
