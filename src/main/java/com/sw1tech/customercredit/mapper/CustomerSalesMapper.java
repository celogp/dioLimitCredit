package com.sw1tech.customercredit.mapper;

import com.sw1tech.customercredit.dto.CustomerSalesDto;
import com.sw1tech.customercredit.entity.CustomerSales;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerSalesMapper {

    CustomerSalesMapper INSTANCE = Mappers.getMapper(CustomerSalesMapper.class);

    CustomerSales toModel(CustomerSalesDto customerSalesDto);

    CustomerSalesDto toDto(CustomerSales customerSales);

}