package com.sw1tech.customercredit.controller;

import com.sw1tech.customercredit.controllerDocs.ICustomerControllerDoc;
import com.sw1tech.customercredit.dto.CustomerDto;
import com.sw1tech.customercredit.dto.CustomerLimitDto;
import com.sw1tech.customercredit.exception.CustomerAlreadyRegisteredException;
import com.sw1tech.customercredit.exception.CustomerLimitCreditOutOfRangeException;
import com.sw1tech.customercredit.exception.CustomerNotFoundException;
import com.sw1tech.customercredit.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController implements ICustomerControllerDoc {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public CustomerDto createCustomer(@RequestBody @Valid CustomerDto customerDto)
            throws CustomerAlreadyRegisteredException, CustomerLimitCreditOutOfRangeException {
        return customerService.createCustomer(customerDto);
    }

    @GetMapping("/{name}")
    @Override
    public CustomerDto findByNameCustomer(@PathVariable String name) throws CustomerNotFoundException {
        return customerService.findByName(name);
    }

    @GetMapping
    @Override
    public List<CustomerDto> listCustomers() {
        return customerService.listAll();
    }

    @PatchMapping("/saveCustomer")
    @Override
    public CustomerDto saveCustomer(@RequestBody @Valid CustomerDto customerDto) throws CustomerNotFoundException,
            CustomerLimitCreditOutOfRangeException {
        return customerService.saveCustomer(customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void eraseCustomer(@PathVariable int id) throws CustomerNotFoundException {
        customerService.deleteById(id);
    }

    @PatchMapping("/saveLimit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void saveLimit(@RequestBody @Valid CustomerLimitDto customerLimitDto) throws CustomerNotFoundException, CustomerLimitCreditOutOfRangeException {
        customerService.saveLimit(customerLimitDto);
    }

}
