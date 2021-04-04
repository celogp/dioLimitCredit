package com.sw1tech.customercredit.mapper;

import com.sw1tech.customercredit.dto.CustomerDto;
import com.sw1tech.customercredit.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toModel(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

}