package com.sw1tech.customercredit.controllerDocs;

import com.sw1tech.customercredit.dto.CustomerDto;
import com.sw1tech.customercredit.dto.CustomerSalesDto;
import com.sw1tech.customercredit.exception.CustomerAlreadyRegisteredException;
import com.sw1tech.customercredit.exception.CustomerLimitCreditOutOfRangeException;
import com.sw1tech.customercredit.exception.CustomerNotFoundException;
import com.sw1tech.customercredit.exception.CustomerSalesExceedLimitCreditException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api("Manages customers sales")
public interface ICustomerSalesControllerDoc {

    @ApiOperation(value = "Customer Sales creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success beer creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    CustomerSalesDto createCustomerSales(CustomerSalesDto customerSalesDto) throws CustomerSalesExceedLimitCreditException;

    @ApiOperation(value = "Returns a list of all customers sales registered.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all sales customers registered."),
    })
    List<CustomerSalesDto> listCustomerSales();

}
