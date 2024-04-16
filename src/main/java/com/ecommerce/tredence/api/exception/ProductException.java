package com.ecommerce.tredence.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ProductException extends ApiException {
    public ProductException(String message, HttpStatus status) {
        super(message, status);
    }
}
