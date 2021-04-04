package com.sw1tech.customercredit.builder;

import com.sw1tech.customercredit.dto.CustomerLimitDto;
import lombok.Builder;

@Builder
public class CustomerLimitDtoBuilder {
    @Builder.Default
    private final int id = 1;

    @Builder.Default
    private final double limitcredit = 0.00;

    public CustomerLimitDto toCustomerLimitDto(){
        return new CustomerLimitDto(id, limitcredit);
    }
}
