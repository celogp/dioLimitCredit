package com.sw1tech.customercredit.service;

import com.sw1tech.customercredit.dto.CustomerDto;
import com.sw1tech.customercredit.entity.Customer;
import com.sw1tech.customercredit.exception.CustomerAlreadyRegisteredException;
import com.sw1tech.customercredit.exception.CustomerLimitCreditOutOfRangeException;
import com.sw1tech.customercredit.exception.CustomerNotFoundException;
import com.sw1tech.customercredit.mapper.CustomerMapper;
import com.sw1tech.customercredit.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    private void verifyIfLimitCreditIsValid(int id, double limitCredit) throws CustomerLimitCreditOutOfRangeException {
        if ((limitCredit < 0.00) || (limitCredit > 1000.00 )) {
            throw new CustomerLimitCreditOutOfRangeException(id, limitCredit);
        }
    }


    private Customer verifyIfExists(int id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    private void verifyIfIsAlreadyRegistered(String name) throws CustomerAlreadyRegisteredException {
        Optional<Customer> optSavedCustomer = customerRepository.findByName(name);
        if (optSavedCustomer.isPresent()) {
            throw new CustomerAlreadyRegisteredException(name);
        }
    }

    public CustomerDto createCustomer(CustomerDto customerDto) throws CustomerAlreadyRegisteredException,
            CustomerLimitCreditOutOfRangeException {

        verifyIfLimitCreditIsValid(0, customerDto.getLimitcredit() );

        verifyIfIsAlreadyRegistered(customerDto.getName());

        Customer customer = customerMapper.toModel(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }

    public CustomerDto findByName(String name) throws CustomerNotFoundException {
        Customer foundCustomer = customerRepository.findByName(name)
                .orElseThrow(() -> new CustomerNotFoundException(name));
        return customerMapper.toDto(foundCustomer);
    }

    public List<CustomerDto> listAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteById(int id) throws CustomerNotFoundException {
        verifyIfExists(id);
        customerRepository.deleteById(id);
    }

    public CustomerDto saveCustomer(CustomerDto customerDto) throws CustomerNotFoundException,
            CustomerLimitCreditOutOfRangeException {

        verifyIfExists(customerDto.getId());
        verifyIfLimitCreditIsValid(customerDto.getId(), customerDto.getLimitcredit() );

        Customer customer = customerMapper.toModel(customerDto);
        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toDto(savedCustomer);
    }


/*    public BeerDTO increment(Long id, int quantityToIncrement) throws BeerNotFoundException, BeerStockExceededException {
        Beer beerToIncrementStock = verifyIfExists(id);
        int quantityAfterIncrement = quantityToIncrement + beerToIncrementStock.getQuantity();
        if (quantityAfterIncrement <= beerToIncrementStock.getMax()) {
            beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantityToIncrement);
            Beer incrementedBeerStock = beerRepository.save(beerToIncrementStock);
            return beerMapper.toDTO(incrementedBeerStock);
        }
        throw new BeerStockExceededException(id, quantityToIncrement);
    }*/

}
