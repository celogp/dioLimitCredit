package com.sw1tech.customercredit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerSalesExceedLimitCreditException extends Exception {
    public CustomerSalesExceedLimitCreditException(int id, double limitCredit) {
        super(String.format("Customer with %s ID purchase exced limit credit : %s", id, limitCredit));
    }
}
