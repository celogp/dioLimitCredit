package com.sw1tech.customercredit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerLimitCreditOutOfRangeException extends Exception {
    public CustomerLimitCreditOutOfRangeException(int id, double limitCredit) {
        super(String.format("Customer with %s ID limit credit not permit : %s", id, limitCredit));
    }
}
