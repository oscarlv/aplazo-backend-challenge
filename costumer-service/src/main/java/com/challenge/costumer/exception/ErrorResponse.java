package com.challenge.costumer.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String code;
    private String error;
    private long timestamp;
    private String message;
    private String path;
}
