package com.sw1tech.customercredit.dto;

import com.sw1tech.customercredit.enums.EDayGood;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private int id;

    @NotNull
    @Size(min = 1, max = 200)
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EDayGood type;

    @NotNull
    private double limitcredit;

}
