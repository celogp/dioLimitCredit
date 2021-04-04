package com.sw1tech.customercredit.service;

import com.sw1tech.customercredit.dto.CustomerSalesDto;
import com.sw1tech.customercredit.entity.Customer;
import com.sw1tech.customercredit.entity.CustomerSales;
import com.sw1tech.customercredit.exception.CustomerSalesExceedLimitCreditException;
import com.sw1tech.customercredit.mapper.CustomerSalesMapper;
import com.sw1tech.customercredit.repository.CustomerRepository;
import com.sw1tech.customercredit.repository.CustomerSalesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerSalesService {

    private final CustomerRepository customerRepository;
    private final CustomerSalesRepository customerSalesRepository;
    private final CustomerSalesMapper customerSalesMapper = CustomerSalesMapper.INSTANCE;


    private void verifyIfLimitSalesIsValid(int idCustomer, double price) throws
            CustomerSalesExceedLimitCreditException {
        Optional<Customer> optCustomer = customerRepository.findById(idCustomer);
        var limitCredit = optCustomer.get().getLimitcredit();
        if (price > limitCredit) {
            throw new  CustomerSalesExceedLimitCreditException(idCustomer, limitCredit);
        }

    }

    public CustomerSalesDto createCustomerSales(CustomerSalesDto customerSalesDto)
            throws CustomerSalesExceedLimitCreditException {

        //verifyIfLimitSalesIsValid( customerSalesDto.getCustomerid()., customerSalesDto.getPrice() );
        verifyIfLimitSalesIsValid( customerSalesDto.getCustomer().getId(), customerSalesDto.getPrice() );



        CustomerSales customerSales = customerSalesMapper.toModel(customerSalesDto);
        CustomerSales savedCustomerSales = customerSalesRepository.save(customerSales);
        return customerSalesMapper.toDto(savedCustomerSales);
    }

    public List<CustomerSalesDto> listAll() {
        return customerSalesRepository.findAll()
                .stream()
                .map(customerSalesMapper::toDto)
                .collect(Collectors.toList());
    }
}
