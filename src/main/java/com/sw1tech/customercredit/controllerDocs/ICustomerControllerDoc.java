package com.sw1tech.customercredit.controllerDocs;

import com.sw1tech.customercredit.dto.CustomerDto;
import com.sw1tech.customercredit.dto.CustomerLimitDto;
import com.sw1tech.customercredit.exception.CustomerAlreadyRegisteredException;
import com.sw1tech.customercredit.exception.CustomerLimitCreditOutOfRangeException;
import com.sw1tech.customercredit.exception.CustomerNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api("Manages customers limits")
public interface ICustomerControllerDoc {

    @ApiOperation(value = "Customer creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success beer creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    CustomerDto createCustomer(@RequestBody @Valid CustomerDto customerDto) throws CustomerAlreadyRegisteredException, CustomerLimitCreditOutOfRangeException;


    @ApiOperation(value = "Returns customer found by at name.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success customer found."),
            @ApiResponse(code = 404, message = "Customer with given name not found.")
    })
    CustomerDto findByNameCustomer(@PathVariable String name) throws CustomerNotFoundException;

    @ApiOperation(value = "Returns a list of all customers registered.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all customers registered."),
    })
    List<CustomerDto> listCustomers();

    @ApiOperation(value = "Returns a customer saved.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer saved."),
    })
    CustomerDto saveCustomer(@RequestBody @Valid CustomerDto customerDto) throws CustomerNotFoundException, CustomerLimitCreditOutOfRangeException;

    @ApiOperation(value = "Erase a customer.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer erase."),
    })
    void eraseCustomer(@PathVariable int id) throws CustomerNotFoundException;

    @ApiOperation(value = "Update limit credit customer.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Customer limit credit updated."),
    })
    void saveLimit(@RequestBody @Valid CustomerLimitDto customerLimitDto)
            throws CustomerNotFoundException, CustomerLimitCreditOutOfRangeException;

}
