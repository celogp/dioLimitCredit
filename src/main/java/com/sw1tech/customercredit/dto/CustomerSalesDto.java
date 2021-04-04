package com.sw1tech.customercredit.dto;

import com.sw1tech.customercredit.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSalesDto {
    private int id;
    private double price;
    private String description;
    private Customer customer;

}
