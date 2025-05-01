package com.aplazo.challenge.util;

import com.aplazo.challenge.exception.CustomerNotFoundException;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@NoArgsConstructor
public class ServiceUtils {

    public static UUID validateAndParseCustomerID(String value) {
        try {
            log.info("Validating customer ID: {}", value);
            return UUID.fromString(value);
        } catch (IllegalArgumentException ex) {
            log.warn("Customer ID {} is not a valid UUID", value, ex);
            throw new CustomerNotFoundException(value);
        }
    }

}
