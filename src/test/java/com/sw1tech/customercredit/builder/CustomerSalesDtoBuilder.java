package com.sw1tech.customercredit.builder;

import com.sw1tech.customercredit.dto.CustomerSalesDto;
import com.sw1tech.customercredit.entity.Customer;
import com.sw1tech.customercredit.enums.EDayGood;
import lombok.Builder;

@Builder
public class CustomerSalesDtoBuilder {
    @Builder.Default
    private final int id = 1;

    @Builder.Default
    private final String description = "Teste unitario";

    @Builder.Default
    private final double price = 10.00;

    @Builder.Default
    private int customerid = 1;

    public CustomerSalesDto toCustomerSalesDto(){
        return new CustomerSalesDto(id, price, description, new Customer(id, description, EDayGood.DAY_10,1000));
    }

}
