package com.example.ecommerce.core.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotFoundException extends RuntimeException {
    private final String message;

    public CustomerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
