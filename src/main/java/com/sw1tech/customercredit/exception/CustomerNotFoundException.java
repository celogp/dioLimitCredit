package com.sw1tech.customercredit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(String name) {
        super(String.format("Customer with name %s not found.", name));
    }

    public CustomerNotFoundException(int id) {
        super(String.format("Customer with id %s not found.", id));
    }
}