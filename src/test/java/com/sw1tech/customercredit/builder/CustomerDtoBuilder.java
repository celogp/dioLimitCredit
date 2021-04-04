package com.sw1tech.customercredit.builder;

import com.sw1tech.customercredit.dto.CustomerDto;
import com.sw1tech.customercredit.enums.EDayGood;
import lombok.Builder;

@Builder
public class CustomerDtoBuilder {
    @Builder.Default
    private final int id = 1;

    @Builder.Default
    private final String name = "Marcelo";

    @Builder.Default
    private final EDayGood type = EDayGood.DAY_10;

    @Builder.Default
    private final double limitcredit = 0.00;

    public CustomerDto toCustomerDto(){
        return new CustomerDto(id, name, type, limitcredit);
    }

}
