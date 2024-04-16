package com.ecommerce.tredence.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ShopperException extends ApiException {
    public ShopperException(String message, HttpStatus status) {
        super(message, status);
    }
}
