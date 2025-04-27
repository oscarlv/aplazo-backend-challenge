package com.challenge.costumer.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

    private final String code;
    private final String error;

    protected BaseException(String code, String error, String message) {
        super(message);
        this.code = code;
        this.error = error;
    }
}