package com.challenge.costumer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ErrorCode.INVALID_CUSTOMER_REQUEST_CODE)
                .error(ErrorCode.INVALID_CUSTOMER_REQUEST)
                .timestamp(Instant.now().getEpochSecond())
                .message(errorMessage)
                .path(getPath(request))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleUnreadableException(HttpMessageNotReadableException ex, WebRequest request) {
        return buildErrorResponse(
                ErrorCode.INVALID_CUSTOMER_REQUEST_CODE,
                ErrorCode.INVALID_CUSTOMER_REQUEST,
                "Invalid request body",
                request,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
        return buildErrorResponse(
                ex.getCode(),
                ex.getError(),
                ex.getMessage(),
                request,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {

        return buildErrorResponse(
                ErrorCode.INTERNAL_SERVER_ERROR_CODE,
                ErrorCode.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                request,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String code, String error, String message, WebRequest request, HttpStatus status) {
        ErrorResponse response = ErrorResponse.builder()
                .code(code)
                .error(error)
                .timestamp(Instant.now().getEpochSecond())
                .message(message)
                .path(getPath(request))
                .build();
        return new ResponseEntity<>(response, status);
    }

    private String getPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }
}
