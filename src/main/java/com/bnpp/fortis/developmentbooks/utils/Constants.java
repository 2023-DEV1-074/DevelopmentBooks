package com.bnpp.fortis.developmentbooks.utils;

import java.math.BigDecimal;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final double NO_DISCOUNT = BigDecimal.ZERO.doubleValue();

    public static final int ZERO_PERCENT = 0;

    public static final int ONE_QUANTITY = 1;

    public static final int HUNDRED = 100;


}
