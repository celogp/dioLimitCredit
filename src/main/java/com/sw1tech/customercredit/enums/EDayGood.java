package com.sw1tech.customercredit.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EDayGood {
    DAY_10(10),
    DAY_15(15),
    DAY_20(20),
    DAY_25(25);

    private final int intDay;
}
