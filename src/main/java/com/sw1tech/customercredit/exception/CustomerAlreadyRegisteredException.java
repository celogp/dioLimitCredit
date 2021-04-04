package com.sw1tech.customercredit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerAlreadyRegisteredException extends Exception{

    public CustomerAlreadyRegisteredException(String name) {
        super(String.format("Customer with name %s already registered.", name));
    }
}
