package com.sw1tech.customercredit.controller;

import com.sw1tech.customercredit.controllerDocs.ICustomerSalesControllerDoc;
import com.sw1tech.customercredit.dto.CustomerSalesDto;
import com.sw1tech.customercredit.exception.CustomerSalesExceedLimitCreditException;
import com.sw1tech.customercredit.service.CustomerSalesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerSalesController implements ICustomerSalesControllerDoc {

    private final CustomerSalesService customerSalesService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public CustomerSalesDto createCustomerSales(@RequestBody @Valid CustomerSalesDto customerSalesDto) throws CustomerSalesExceedLimitCreditException {
        return customerSalesService.createCustomerSales(customerSalesDto);
    }

    @GetMapping
    @Override
    public List<CustomerSalesDto> listCustomerSales() {
        return customerSalesService.listAll();
    }

}